package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.UserDeleteResponse;
//import com.upgrad.quora.service.business.UserBusinessService;
//import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.business.UserService;
import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import javafx.scene.media.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/{userId}" ,method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)                    //try out all possibilities with attributes
    //note that label for path variable is same as defined in json file
    public ResponseEntity<UserDeleteResponse> deleteUser(@PathVariable("userId") String userId, @RequestHeader("authorization") String accessToken) {

//do all the validation before deleting the user
        UserEntity entity = userService.deleteUser(accessToken, userId);

        UserDeleteResponse userDeleteResponse = new UserDeleteResponse();
        userDeleteResponse.setId(entity.getUuid());
        userDeleteResponse.setStatus("User succefully deleted");
        return new ResponseEntity<UserDeleteResponse>(userDeleteResponse, HttpStatus.OK);
    }
}
