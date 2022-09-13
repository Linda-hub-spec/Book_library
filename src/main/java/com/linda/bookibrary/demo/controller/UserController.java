package com.linda.bookibrary.demo.controller;

import com.linda.bookibrary.demo.dto.response.BaseResponse;
import com.linda.bookibrary.demo.dto.request.CreateUserRequest;
import com.linda.bookibrary.demo.dto.request.LoginRequest;
import com.linda.bookibrary.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author linda.agbaka
 */

@RestController
@CrossOrigin
@RequestMapping("/api/library")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserRequest request){
        BaseResponse response = userService.createUser(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin(LoginRequest request){
        BaseResponse response = userService.userLogin(request);
        return ResponseEntity.ok().body(response);
    }
}
