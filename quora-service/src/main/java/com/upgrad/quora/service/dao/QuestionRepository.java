package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.QuestionEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class QuestionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void storeQuestion(QuestionEntity questionEntity) {
        entityManager.persist(questionEntity);
    }


    public QuestionEntity getQuestion(String uuid) {

        QuestionEntity entity = new QuestionEntity();
        try {
            TypedQuery<QuestionEntity> query = entityManager.createNamedQuery("findByUuid", QuestionEntity.class);
            query.setParameter("questionId", uuid);
            entity = query.getSingleResult();

        } catch (NoResultException e) {

            //log
        }

        return entity;
    }

    public QuestionEntity updateYourQuestion(QuestionEntity entity) {

        entityManager.persist(entity);

        return entity;
    }

    public QuestionEntity deleteQuestion(QuestionEntity entity){

        entityManager.remove(entity);
        return entity;
    }

    public List<QuestionEntity> getAllQuestionsOfUser(String userId) {
        List<QuestionEntity> questionList = null;
        try {
            TypedQuery<QuestionEntity> query = entityManager.createNamedQuery("findQuestionsByUserId", QuestionEntity.class);  //note the typedquery is of type entity class only, even when query returns list
            query.setParameter("userId", userId);
            questionList = query.getResultList();

        } catch (NoResultException e) {


        }
        return questionList;
    }
}