package com.upgrad.quora.service.business;


import com.upgrad.quora.service.dao.UserRepository;
import com.upgrad.quora.service.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
            if(userEntity.getPassword().equals(passwordCryptographyProvider.encrypt(password,userEntity.getSalt())))  //understand this overloaded method, and we need to call it while login
            {
                //generate token and persist data in userAuth

            }
            else{

                //scenario for password validation fail
            }
        }
       else{
           //scenario for username validation fail
        }
        return userEntity;
    }
}


//always good to draw a flowchart before getting start with logic