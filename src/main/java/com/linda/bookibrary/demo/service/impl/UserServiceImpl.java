package com.linda.bookibrary.demo.service.impl;

import com.linda.bookibrary.demo.dto.response.BaseResponse;
import com.linda.bookibrary.demo.dto.request.CreateUserRequest;
import com.linda.bookibrary.demo.dto.request.LoginRequest;
import com.linda.bookibrary.demo.entity.User;
import com.linda.bookibrary.demo.enums.ResponseEnum;
import com.linda.bookibrary.demo.repositories.UserRepository;
import com.linda.bookibrary.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    

    @Override
    public BaseResponse createUser(CreateUserRequest request) {
        Base64.Encoder encoder = Base64.getEncoder();

        Long startTime = System.currentTimeMillis();
        BaseResponse response = new BaseResponse();

        try {
            //checks if user with email already exist
            Optional<User> user = userRepository.findByEmail(request.getEmail());
            if (user.isPresent()) {
                response.setResponseCode(ResponseEnum.USER_EXIST.getResponseCode());
                response.setResponseMessage(ResponseEnum.USER_EXIST.getResponseMessage());
                response.setExecTime(System.currentTimeMillis() - startTime);
                return response;
            }
            User userData = new User();
            userData.setFirstname(request.getFirstname());
            userData.setLastname(request.getLastname());
            userData.setEmail(request.getEmail());
            userData.setUserType(request.getUserType());
            userData.setPassword(encoder.encodeToString(request.getPassword().getBytes()));
            userRepository.save(userData);

            response.setResponseCode(ResponseEnum.SUCCESSFUL.getResponseCode());
            response.setResponseMessage(ResponseEnum.SUCCESSFUL.getResponseMessage());
            response.setExecTime(System.currentTimeMillis() - startTime);
            return response;

        }catch (Exception ex){
            response.setResponseCode(ResponseEnum.ERROR.getResponseCode());
            response.setResponseMessage(ResponseEnum.ERROR.getResponseMessage()+ " "+ ex.getStackTrace());
            response.setExecTime(System.currentTimeMillis() - startTime);
            return response;
        }

    }

    @Override
    public BaseResponse userLogin(LoginRequest request) {
        return null;
    }
}
