package com.linda.bookibrary.demo.dto.request;

import com.linda.bookibrary.demo.constant.MessageConstant;
import com.linda.bookibrary.demo.enums.UserType;

import lombok.Data;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @author linda.agbaka
 */

@Data
public class CreateUserRequest {

    @NotBlank(message = MessageConstant.FIRSTNAME)
    private String firstname;

    @NotBlank(message = MessageConstant.LASTNAME)
    private String lastname;

    @Email(message = MessageConstant.EMAIL_VALID)
    @NotBlank(message = MessageConstant.EMAIL)
    private String email;

    @NotBlank(message = MessageConstant.PASSWORD)
    private String password;

    @NotNull(message = MessageConstant.USERTYPE)
    private UserType userType;
}
