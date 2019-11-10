package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.UserDetailsResponse;
import com.upgrad.quora.service.business.UserService;
import com.upgrad.quora.service.entity.UserAuthenticationEntity;
import com.upgrad.quora.service.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import com.upgrad.quora.service.business.UserBusinessService;
//import com.upgrad.quora.service.entity.UserEntity;

@RestController
@RequestMapping("/")
public class CommonController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "userprofile/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDetailsResponse> getUserDetails(@RequestHeader("authorization") String accessToken, @PathVariable("userId") String uuid) {
UserAuthenticationEntity userAuthenticationEntity=userService.getUserByAccessToken(accessToken);
    UserEntity entity=userService.getUserDetails(uuid);   //avoid unnecessary null checks if its already been taken care in inner module
UserDetailsResponse userDetailsResponse=new UserDetailsResponse();
BeanUtils.copyProperties(entity,userDetailsResponse); //note appropriate mapping between fields / field names
        return new ResponseEntity<UserDetailsResponse>(userDetailsResponse, HttpStatus.OK);
    }

}
