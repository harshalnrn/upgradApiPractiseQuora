package com.upgrad.quora.service.business;


import com.upgrad.quora.service.dao.UserRepository;
import com.upgrad.quora.service.entity.UserAuthenticationEntity;
import com.upgrad.quora.service.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordCryptographyProvider passwordCryptographyProvider;


    public void getNewUserDetailsToSave(UserEntity userEntity) throws DataAccessException {
        userRepository.insertNewUser(userEntity);
    }

    public UserEntity ValidateSignedInUser(String username, String password) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity != null) {
            if (userEntity.getPassword().equals(passwordCryptographyProvider.encrypt(password, userEntity.getSalt())))  //understand this overloaded method, and we need to call it while login
            {
                //generate token and persist data in userAuth
                JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(password);
                //timestamp without timezone
                ZonedDateTime issuedTime = ZonedDateTime.now();
                ZonedDateTime expiryTime = ZonedDateTime.now().plusDays(1);
                String accessToken = jwtTokenProvider.generateToken(userEntity.getUuid(), issuedTime, expiryTime);  //understand the token generation algorithm

                UserAuthenticationEntity userAuthenticationEntity = new UserAuthenticationEntity();
                userAuthenticationEntity.setAccessToken(accessToken);
                userAuthenticationEntity.setExpiresAt(expiryTime);
                userAuthenticationEntity.setLoginAt(issuedTime);
                userAuthenticationEntity.setLogoutAt(null);
                //userAuthenticationEntity.setUserId(userEntity.getId());
                userAuthenticationEntity.setUuid(UUID.randomUUID().toString());
                userAuthenticationEntity.setUserEntity(userEntity);
                userRepository.insertUserAuth(userAuthenticationEntity);
            } else {

                //scenario for password validation fail
            }
        } else {
            //scenario for username validation fail
        }
        return userEntity;
    }

    public UserAuthenticationEntity getUserByAccessToken(String accessToken) {
        UserAuthenticationEntity userAuthenticationEntity = userRepository.getByAccessToken(accessToken);
        if (userAuthenticationEntity == null) {
            //throw exception
        }
        return userAuthenticationEntity;
    }

    public UserAuthenticationEntity logoutUser(String accessToken) {
        UserAuthenticationEntity userAuthenticationEntity = getUserByAccessToken(accessToken);
        if (userAuthenticationEntity != null) {
            userAuthenticationEntity = userRepository.logOutUser(userAuthenticationEntity);
        } else {
            // throw exception
        }
        return userAuthenticationEntity;
    }


    public UserEntity getUserDetails(String userId) {
        UserEntity entity = null;
        entity = userRepository.findByUserId(userId);
        if (entity == null) {
            //throw exception
        }
        return entity;
    }

    public UserEntity deleteUser(String accessToken, String userId) {
        UserEntity entity = null;
        UserAuthenticationEntity userAuthenticationEntity = userRepository.getByAccessToken(accessToken);
        if (userAuthenticationEntity != null) {
            entity = getUserDetails(userId);
            if (entity != null) {
                userRepository.deleteUser(entity);
            }
        } else {
            //throw exception
        }
        return entity;
    }
}


//always good to draw a flowchart before getting start with logic

