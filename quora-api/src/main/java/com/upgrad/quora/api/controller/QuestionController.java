package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import com.upgrad.quora.service.business.QuestionBusinessService;
//import com.upgrad.quora.service.entity.QuestionsEntity;

/**
 * This is a controller class for handling http requests related to Question Management
 * functionalities of quora application
 */
@RestController
@RequestMapping("/question")
public class QuestionController {


    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionResponse> createQuestion(@RequestHeader("authorization") String accessToken, QuestionRequest questionRequest) {


        QuestionResponse questionResponse = new QuestionResponse();
        return new ResponseEntity<QuestionResponse>(questionResponse, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionDetailsResponse> getAllQuoraQuestions(@RequestHeader("authorization") String accessToken) {

        QuestionDetailsResponse questionDetailsResponse = new QuestionDetailsResponse();
        return new ResponseEntity<QuestionDetailsResponse>(questionDetailsResponse, HttpStatus.OK);
    }


    @RequestMapping(value = "/edit/{questionId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionEditResponse> editYourQuestion(@RequestHeader("authorization") String accessToken, @PathVariable("questionId") String questionId, QuestionEditRequest questionEditRequest) {

        QuestionEditResponse questionEditResponse = new QuestionEditResponse();
        return new ResponseEntity<>(questionEditResponse, HttpStatus.OK);
    }


    @RequestMapping(value = "/delete/{questionId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

    public ResponseEntity<QuestionDeleteResponse> deleteYourQuestion(@RequestHeader("authorization") String accessToken, @PathVariable("questionId") String questionId) {

        QuestionDeleteResponse questionDeleteResponse = new QuestionDeleteResponse();
        return new ResponseEntity<QuestionDeleteResponse>(questionDeleteResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/all/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionDetailsResponse> getQuestionsOfUser(@RequestHeader("authorization") String accessToken, @PathVariable("userId") String userId) {

        QuestionDetailsResponse questionDetailsResponse = new QuestionDetailsResponse();
        return new ResponseEntity<QuestionDetailsResponse>(questionDetailsResponse, HttpStatus.OK);
    }
}
