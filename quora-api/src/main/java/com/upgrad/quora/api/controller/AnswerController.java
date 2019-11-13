package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.*;
//import com.upgrad.quora.service.business.AnswerBusinessService;
//import com.upgrad.quora.service.entity.AnswerEntity;
import com.upgrad.quora.service.business.AnswerService;
import com.upgrad.quora.service.business.QuestionService;
import com.upgrad.quora.service.business.UserService;
import com.upgrad.quora.service.entity.AnswerEntity;
import com.upgrad.quora.service.entity.QuestionEntity;
import com.upgrad.quora.service.entity.UserAuthenticationEntity;
import com.upgrad.quora.service.exception.AnswerNotFoundException;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.InvalidQuestionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.awt.image.BandedSampleModel;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * This controller class manages the answer management functionalities
 */
@RestController
@RequestMapping("/")                  //class level mapping mandatory
public class AnswerController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = "question/{questionId}/answer/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AnswerResponse> createAnswer(@RequestHeader("authorization") String authorization, @PathVariable("questionId") String questionId, AnswerRequest answerRequest) {

        UserAuthenticationEntity userAuthenticationEntity = userService.getUserByAccessToken(authorization);

        QuestionEntity questionEntity = questionService.getQuestion(questionId);

        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAns(answerRequest.getAnswer());
        answerEntity.setDate(ZonedDateTime.now());
        answerEntity.setQuestionEntity(questionEntity);
        answerEntity.setUserEntity(userAuthenticationEntity.getUserEntity());
        answerEntity.setUuid(UUID.randomUUID().toString());
        answerService.createAnswer(answerEntity);
        AnswerResponse answerResponse = new AnswerResponse();
        answerResponse.setId(answerEntity.getUuid());
        answerResponse.setStatus("answer created successfully");
        return new ResponseEntity<AnswerResponse>(answerResponse, HttpStatus.CREATED);
    }


    @RequestMapping(value = "answer/edit/{answerId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AnswerEditResponse> editAnswer(@RequestHeader("authorization") String authorization, @PathVariable("answerId") String answerId, AnswerEditRequest answerEditRequest) {

        UserAuthenticationEntity userAuthenticationEntity = userService.getUserByAccessToken(authorization);

        AnswerEntity answerEntity = answerService.getAnswer(answerId);

        answerEntity.setDate(ZonedDateTime.now());
        answerEntity.setAns(answerEditRequest.getContent());
        answerEntity = answerService.updateAnswer(answerEntity);
        AnswerEditResponse answerEditResponse = new AnswerEditResponse();
        answerEditResponse.setId(answerEntity.getUuid());
        answerEditResponse.setStatus("answer updated succesfully");
        return new ResponseEntity<AnswerEditResponse>(answerEditResponse, HttpStatus.OK);
    }


    @RequestMapping(value = "answer/delete/{answerId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AnswerDeleteResponse> deleteAnswer(@RequestHeader("authorization") String authorization, @PathVariable("answerId") String answerId) {
        UserAuthenticationEntity userAuthenticationEntity = userService.getUserByAccessToken(authorization);
        AnswerEntity entity = answerService.getAnswer(answerId);

        entity = answerService.deleteAnswer(entity);
        AnswerDeleteResponse answerDeleteResponse = new AnswerDeleteResponse();
        answerDeleteResponse.setId(entity.getUuid());
        answerDeleteResponse.setStatus("answer deleted succesfully");
        return new ResponseEntity<AnswerDeleteResponse>(answerDeleteResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "answer/all/{questionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AnswerDetailsResponse>> getAnswersOfQuestion(@RequestHeader("authorization") String authorization, @PathVariable("questionId") String questionId) {
        UserAuthenticationEntity userAuthenticationEntity = userService.getUserByAccessToken(authorization);
        List<AnswerEntity> entityList = answerService.getAnswerList(questionId);

        List<AnswerDetailsResponse> answerList = new LinkedList<AnswerDetailsResponse>();

        for (AnswerEntity entity : entityList) {
            AnswerDetailsResponse answerDetailsResponse = new AnswerDetailsResponse();
            answerDetailsResponse.setId(entity.getUuid());
            answerDetailsResponse.setAnswerContent(entity.getAns());
            answerDetailsResponse.setQuestionContent(entity.getQuestionEntity().getContent());
            answerList.add(answerDetailsResponse);
        }
        return new ResponseEntity<List<AnswerDetailsResponse>>(answerList, HttpStatus.OK);
    }

}
