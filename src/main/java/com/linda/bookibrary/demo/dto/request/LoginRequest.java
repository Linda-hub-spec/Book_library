package com.linda.bookibrary.demo.dto.request;

import com.linda.bookibrary.demo.constant.MessageConstant;

import lombok.Data;


import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {

    @NotEmpty(message =MessageConstant.EMAIL)
    private String email;

    @NotEmpty(message = MessageConstant.PASSWORD)
    private String password;
}
