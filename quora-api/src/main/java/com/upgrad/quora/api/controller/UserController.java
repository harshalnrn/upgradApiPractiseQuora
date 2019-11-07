package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.SigninResponse;
import com.upgrad.quora.api.model.SignoutResponse;
import com.upgrad.quora.api.model.SignupUserRequest;
import com.upgrad.quora.api.model.SignupUserResponse;
//import com.upgrad.quora.service.business.UserBusinessService;
//import com.upgrad.quora.service.entity.UserAuthTokenEntity;
//import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.business.PasswordCryptographyProvider;
import com.upgrad.quora.service.business.UserService;
import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.AuthenticationFailedException;
import com.upgrad.quora.service.exception.SignOutRestrictedException;
import com.upgrad.quora.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.Base64;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordCryptographyProvider passwordCryptographyProvider;

    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SignupUserResponse> signUp(final SignupUserRequest signupUserRequest) {
        String[] encryptedDetails=passwordCryptographyProvider.encrypt(signupUserRequest.getPassword());
        UserEntity userEntity = new UserEntity();
        userEntity.setUuid(UUID.randomUUID().toString());
        userEntity.setAboutme(signupUserRequest.getAboutMe());
        userEntity.setContactnumber(signupUserRequest.getContactNumber());
        userEntity.setCountry(signupUserRequest.getCountry());
        userEntity.setDob(signupUserRequest.getDob());
        userEntity.setFirstname(signupUserRequest.getFirstName());
        userEntity.setUsername(signupUserRequest.getUserName());
        userEntity.setSalt(encryptedDetails[0]);  //note the algorithm and API used for this
        userEntity.setRole("nonadmin");  //by default non admin while login . for now
        userEntity.setLastname(signupUserRequest.getLastName());
        userEntity.setPassword(encryptedDetails[1]); //note the SHAencryption algorithm and API used for this
        userEntity.setEmail(signupUserRequest.getEmailAddress());

userService.getNewUserDetailsToSave(userEntity);
SignupUserResponse signupUserResponse = new SignupUserResponse();
signupUserResponse.setId(userEntity.getUuid());
signupUserResponse.setStatus("USER SUCCESSFULLY REGISTERED");
        return new ResponseEntity<SignupUserResponse>(signupUserResponse, HttpStatus.CREATED);
    }



@RequestMapping(value = "/signin",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SigninResponse> signInUser(@RequestHeader("authorization") String authorization){

        String[] credentials=authorization.split("Basic ");
    byte[] array=Base64.getDecoder().decode(credentials[1]);
    String decodedCredentials=new String(array);
    String[] finalCrdentials=decodedCredentials.split(":");

UserEntity userEntity=userService.ValidateSignedInUser(finalCrdentials[0],finalCrdentials[1]);

//also avoid unncessary null checks in every layer in rest api .

        SigninResponse signinResponse=new SigninResponse();
        signinResponse.setId(userEntity.getUuid());
        signinResponse.setMessage("SIGNED IN SUCCESSFULLY");
return new ResponseEntity<SigninResponse>(signinResponse,HttpStatus.OK);
    }
}
