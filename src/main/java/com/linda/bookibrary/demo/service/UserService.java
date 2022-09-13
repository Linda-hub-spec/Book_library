package com.linda.bookibrary.demo.service;

import com.linda.bookibrary.demo.dto.response.BaseResponse;
import com.linda.bookibrary.demo.dto.request.CreateUserRequest;
import com.linda.bookibrary.demo.dto.request.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    BaseResponse createUser(CreateUserRequest request);

    BaseResponse userLogin(LoginRequest request);
}
