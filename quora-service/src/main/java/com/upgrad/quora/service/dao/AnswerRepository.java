package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.AnswerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AnswerRepository {
    @PersistenceContext
    EntityManager entityManager;

    public AnswerEntity createAnswer(AnswerEntity entity) {

        entityManager.persist(entity);

        return entity;
    }

    public AnswerEntity getAnswer(String answerUuid) {
        AnswerEntity entity = null;
        try {
            TypedQuery<AnswerEntity> query = entityManager.createNamedQuery("findByAnswerId", AnswerEntity.class);
            query.setParameter("answerId", answerUuid);
            entity = query.getSingleResult();
        } catch (NoResultException e) {
//log

        }
        return entity;
    }

    public AnswerEntity deleteAnswer(AnswerEntity answerEntity) {

        entityManager.remove(answerEntity);
        return answerEntity;
    }

    public List<AnswerEntity> getListOfAnswer(String questionId) {

        List<AnswerEntity> answersList = null;
        try {
            TypedQuery<AnswerEntity> query = entityManager.createNamedQuery("getListOfAnswers", AnswerEntity.class);
            query.setParameter("questionId", questionId);
            answersList = query.getResultList();
        } catch (NoResultException e) {


        }
        return answersList;
    }
    public AnswerEntity updateAnswer( AnswerEntity answer){

        entityManager.persist(answer);
        return answer;
    }

}
