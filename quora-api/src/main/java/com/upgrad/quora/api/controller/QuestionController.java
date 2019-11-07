package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.*;
//import com.upgrad.quora.service.business.QuestionBusinessService;
//import com.upgrad.quora.service.entity.QuestionsEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.InvalidQuestionException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * This is a controller class for handling http requests related to Question Management
 * functionalities of quora application
 */
@RestController
@RequestMapping("/question")
public class QuestionController {


}
