package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.QuestionEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class QuestionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void storeQuestion(QuestionEntity questionEntity) {
        entityManager.persist(questionEntity);
    }
}
