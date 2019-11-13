package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.QuestionRepository;
import com.upgrad.quora.service.entity.AnswerEntity;
import com.upgrad.quora.service.entity.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public QuestionEntity storeNewQuestion(QuestionEntity questionEntity) {
        questionRepository.storeQuestion(questionEntity);
        return questionEntity;
    }

    public QuestionEntity getQuestion(String uuid) {

        QuestionEntity questionEntity = questionRepository.getQuestion(uuid);
        if (questionEntity == null) {
            //exception
        }

        return questionEntity;
    }

    public QuestionEntity updateQuestion(QuestionEntity entity) {

        entity = questionRepository.updateYourQuestion(entity);
        if (entity == null) {
            //throw exception
        }
        return entity;
    }


    public QuestionEntity deleteQuestion(QuestionEntity entity) {

        entity = questionRepository.deleteQuestion(entity);
        if (entity == null) {
            //throw exception
        }
        return entity;
    }

    public List<QuestionEntity> getAllQuestionsOfUser(String userId) {

        List<QuestionEntity> questionList = questionRepository.getAllQuestionsOfUser(userId);
        if (questionList == null) {
            //exception
        }
        return questionList;
    }

    public List<QuestionEntity> getAllQuestions() {
        List<QuestionEntity> questionsList = questionRepository.getAllQuestionsOfAllUsers();
        if (questionsList == null) {
            //exception
        }
        return questionsList;
    }



}