package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.*;
import com.upgrad.quora.service.business.QuestionService;
import com.upgrad.quora.service.business.UserService;
import com.upgrad.quora.service.entity.QuestionEntity;
import com.upgrad.quora.service.entity.UserAuthenticationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QuerydslUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

//import com.upgrad.quora.service.business.QuestionBusinessService;
//import com.upgrad.quora.service.entity.QuestionsEntity;

/**
 * This is a controller class for handling http requests related to Question Management
 * functionalities of quora application
 */
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionResponse> createQuestion(@RequestHeader("authorization") String accessToken, QuestionRequest questionRequest) {

        UserAuthenticationEntity userAuthenticationEntity = userService.getUserByAccessToken(accessToken);
        QuestionEntity questionEntity = new QuestionEntity();

        questionEntity.setContent(questionRequest.getContent());
        questionEntity.setDate(ZonedDateTime.now());
        questionEntity.setUserId(userAuthenticationEntity.getUserEntity());
        questionEntity.setUuid(UUID.randomUUID().toString());
        questionService.storeNewQuestion(questionEntity);
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setId(questionEntity.getUuid());
        questionResponse.setStatus("question succesfully created and store");
        return new ResponseEntity<QuestionResponse>(questionResponse, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<QuestionDetailsResponse>> getAllQuoraQuestions(@RequestHeader("authorization") String accessToken) {
        List<QuestionEntity> questionList = questionService.getAllQuestions();
        List<QuestionDetailsResponse> responseList = new LinkedList<QuestionDetailsResponse>();

        for (QuestionEntity entity : questionList) {
            QuestionDetailsResponse questionDetailsResponse = new QuestionDetailsResponse();
            questionDetailsResponse.setId(entity.getUuid());
            questionDetailsResponse.setContent(entity.getContent());
            responseList.add(questionDetailsResponse);
        }

        return new ResponseEntity<List<QuestionDetailsResponse>>(responseList, HttpStatus.OK);
    }


    @RequestMapping(value = "/edit/{questionId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionEditResponse> editYourQuestion(@RequestHeader("authorization") String accessToken, @PathVariable("questionId") String questionId, QuestionEditRequest questionEditRequest) {
//check if its existing user and logged in

        UserAuthenticationEntity userAuthenticationEntity = userService.getUserByAccessToken(accessToken);
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setUserId(userAuthenticationEntity.getUserEntity());
        questionEntity.setUuid(UUID.randomUUID().toString());
        questionEntity.setDate(ZonedDateTime.now());
        questionEntity.setContent(questionEditRequest.getContent());
        if (userAuthenticationEntity != null) {
            //check if this question belongs to user
            questionEntity = questionService.getQuestion(questionId);
            if (questionEntity != null && questionEntity.getUserId().getId().equals(userAuthenticationEntity.getUserEntity().getId())) {
                questionEntity = questionService.storeNewQuestion(questionEntity);
            }
        }
        QuestionEditResponse questionEditResponse = new QuestionEditResponse();
        questionEditResponse.setId(questionEntity.getUuid());
        questionEditResponse.setStatus("question updated succesfully");
        return new ResponseEntity<QuestionEditResponse>(questionEditResponse, HttpStatus.OK);
    }


    @RequestMapping(value = "/delete/{questionId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

    public ResponseEntity<QuestionDeleteResponse> deleteYourQuestion(@RequestHeader("authorization") String accessToken, @PathVariable("questionId") String questionId) {
        UserAuthenticationEntity userAuthenticationEntity = userService.getUserByAccessToken(accessToken);
        QuestionEntity questionEntity = null;
        if (userAuthenticationEntity != null) {
            //check if this question belongs to user
            questionEntity = questionService.getQuestion(questionId);
            if (questionEntity != null && (questionEntity.getUserId().getId().equals(userAuthenticationEntity.getUserEntity().getId()) || questionEntity.getUserId().getRole().equals("admin"))) {

                questionEntity = questionService.deleteQuestion(questionEntity);
            }
        }
        QuestionDeleteResponse questionDeleteResponse = new QuestionDeleteResponse();
        questionDeleteResponse.setId(questionEntity.getUuid());
        questionDeleteResponse.setStatus("question deleted succesfully");
        return new ResponseEntity<QuestionDeleteResponse>(questionDeleteResponse, HttpStatus.OK);
    }


    @RequestMapping(value = "/all/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<QuestionDetailsResponse>> getQuestionsOfUser(@RequestHeader("authorization") String
                                                                                    accessToken, @PathVariable("userId") String userId) {
        UserAuthenticationEntity userAuthenticationEntity = userService.getUserByAccessToken(accessToken);
        List<QuestionEntity> questionList = null; //list of entity
        List<QuestionDetailsResponse> questionResponseList = null; //list of model

        if (userAuthenticationEntity != null) {
            questionList = questionService.getAllQuestionsOfUser(userId);
            questionResponseList = new LinkedList<QuestionDetailsResponse>();
            for (QuestionEntity entity : questionList) {
                QuestionDetailsResponse questionDetailsResponse = new QuestionDetailsResponse();
                questionDetailsResponse.setContent(entity.getContent());
                questionDetailsResponse.setId(entity.getUuid());
                questionResponseList.add(questionDetailsResponse);
            }

        }
        return new ResponseEntity<List<QuestionDetailsResponse>>(questionResponseList, HttpStatus.OK);
    }
}


//why send response entity object ??