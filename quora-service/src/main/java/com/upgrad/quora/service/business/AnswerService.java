package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.AnswerRepository;
import com.upgrad.quora.service.entity.AnswerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;


public AnswerEntity createAnswer(AnswerEntity answerEntity){
answerEntity = answerRepository.createAnswer(answerEntity);
return answerEntity;
}


public AnswerEntity getAnswer(String answerId){

    AnswerEntity answerEntity=answerRepository.getAnswer(answerId);
    if(answerEntity==null){
        //throw exception
    }
    return answerEntity;
}

public AnswerEntity deleteAnswer(AnswerEntity answerEntity){
    answerEntity=answerRepository.deleteAnswer(answerEntity);
    return answerEntity;
}

public List<AnswerEntity> getAnswerList(String questionId){
    List<AnswerEntity> answerList=answerRepository.getListOfAnswer(questionId);
    if(answerList==null){
        //throw exception
    }
    return answerList;
}

    public AnswerEntity updateAnswer(AnswerEntity answerEntity){
answerEntity=answerRepository.updateAnswer(answerEntity);
return answerEntity;
    }
}
