package com.upgrad.quora.api.exception;


import com.upgrad.quora.api.model.ErrorResponse;
import com.upgrad.quora.service.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(SignOutRestrictedException.class)
  public ResponseEntity<ErrorResponse> singOutRestrictedException(SignOutRestrictedException exe, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(new ErrorResponse().code(exe.getCode()).message(exe.getErrorMessage()) , HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(SignUpRestrictedException.class)
  public ResponseEntity<ErrorResponse> resourceAlreadyExistsException(SignUpRestrictedException exc, WebRequest webRequest) {
    return new ResponseEntity<ErrorResponse>(new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage()), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(AuthenticationFailedException.class)
  public ResponseEntity<ErrorResponse> authenticationFailedException(AuthenticationFailedException exc, WebRequest webRequest) {
    return new ResponseEntity<ErrorResponse>(new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage()), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(AuthorizationFailedException.class)
  public ResponseEntity<ErrorResponse> unauthorizedException(AuthorizationFailedException exe, WebRequest webRequest) {
    return new ResponseEntity<ErrorResponse>(new ErrorResponse().code(exe.getCode()).message(exe.getErrorMessage()), HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorResponse> resourceNotFoundException(UserNotFoundException exe, WebRequest webRequest) {
    return new ResponseEntity<ErrorResponse>(new ErrorResponse().code(exe.getCode()).message(exe.getErrorMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InvalidQuestionException.class)
  public ResponseEntity<ErrorResponse> resourceNotFoundException(InvalidQuestionException e, WebRequest webRequest){
      return new ResponseEntity<ErrorResponse>(new ErrorResponse().code(e.getCode()).message(e.getErrorMessage()),HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AnswerNotFoundException.class)
  public ResponseEntity<ErrorResponse> resourceNotFoundException(AnswerNotFoundException e, WebRequest webRequest){
    return new ResponseEntity<ErrorResponse>(new ErrorResponse().code(e.getCode()).message(e.getErrorMessage()),HttpStatus.NOT_FOUND);
  }
}

