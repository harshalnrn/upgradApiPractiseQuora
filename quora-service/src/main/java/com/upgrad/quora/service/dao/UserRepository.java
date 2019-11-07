package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.UserEntity;
import org.apache.commons.lang3.reflect.Typed;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class UserRepository {


    @PersistenceContext
    EntityManager entityManager;


    //auto configuration operations: example db happens only if configurations at application level in app.properties ?
    //what is configurations stored on server level

    public void insertNewUser(UserEntity userEntity) throws DataAccessException {
        entityManager.persist(userEntity);
    }


    public UserEntity findByUsername(String username) {
        UserEntity userEntity = null;
        try {
            TypedQuery<UserEntity> query = entityManager.createNamedQuery("findByUsername", UserEntity.class);
            query.setParameter("username", username);
            userEntity = query.getSingleResult();
        } catch (NoResultException e) {
//log
            //what is better: throwing custome exception here itself, or in service , by doing a null check
        }

        return userEntity;
    }

}