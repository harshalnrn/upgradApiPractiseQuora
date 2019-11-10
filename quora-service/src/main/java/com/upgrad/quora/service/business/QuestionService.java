package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.QuestionRepository;
import com.upgrad.quora.service.entity.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public QuestionEntity storeNewQuestion(QuestionEntity questionEntity) {
        questionRepository.storeQuestion(questionEntity);
        return questionEntity;
    }
}
