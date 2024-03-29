package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.QuestionEntity;
import com.upgrad.quora.service.entity.UserAuthenticationEntity;
import com.upgrad.quora.service.entity.UserEntity;
import org.apache.commons.lang3.reflect.Typed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public class UserRepository {


    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserDao userDao;           //how did you instantiate an interface here ??
    @Autowired
    private UserAuthenticationDao userAuthenticationDao;
    //auto configuration operations: example db happens only if configurations at application level in app.properties ?
    //what is configurations stored on server level

    public void insertNewUser(UserEntity userEntity) throws DataAccessException {
        // entityManager.persist(userEntity);
        userDao.save(userEntity);

    }


    public UserEntity findByUsername(String username) {
        UserEntity userEntity = null;
        try {
            TypedQuery<UserEntity> query = entityManager.createNamedQuery("findByUsername", UserEntity.class);
            query.setParameter("userName", username);
            userEntity = query.getSingleResult();
        } catch (NoResultException e) {
//log
            //what is better: throwing custome exception here itself, or in service , by doing a null check
        }

        return userEntity;
    }


    public void insertUserAuth(UserAuthenticationEntity userAuthenticationEntity) {

        // entityManager.persist(userAuthenticationEntity); //constraint validation and persist  //note data access exception is important

        userAuthenticationDao.save(userAuthenticationEntity);
    }


    public UserAuthenticationEntity getByAccessToken(String accessToken) {
        UserAuthenticationEntity userAuthenticationEntity = null;
        try {
            TypedQuery<UserAuthenticationEntity> query = entityManager.createNamedQuery("findByToken", UserAuthenticationEntity.class);
            query.setParameter("accessToken", accessToken);
            userAuthenticationEntity = query.getSingleResult();
        } catch (NoResultException e) {
//log
        }
        return userAuthenticationEntity;
    }

    public UserAuthenticationEntity logOutUser(UserAuthenticationEntity userAuthenticationEntity) {

        userAuthenticationEntity.setLogoutAt(ZonedDateTime.now());
        // userAuthenticationEntity = entityManager.merge(userAuthenticationEntity); //persist vs merge ? //what if any exception here ?
        userAuthenticationEntity = userAuthenticationDao.save(userAuthenticationEntity);
        return userAuthenticationEntity;
    }


    public UserEntity findByUserId(String userId) {
        UserEntity userEntity = null;
        try {
            TypedQuery<UserEntity> query = entityManager.createNamedQuery("findByUserId", UserEntity.class);
            query.setParameter("uuid", userId);
            userEntity = query.getSingleResult();
        } catch (NoResultException e) {

        }
        return userEntity;
    }


    public void deleteUser(UserEntity entity) {

        //entityManager.remove(entity);   //manage exception   //remove vs detach?

        userDao.delete(entity);

    }


}