package com.linda.bookibrary.demo.enums;

public enum ResponseEnum {

    SUCCESSFUL("200", "Successful"),
    USER_EXIST("400", "User with Email already exist"),
    USER_DOES_NOT_EXIST("400", "User does not exist"),
    BOOK_DOES_NOT_EXIST("400", "Book with Id does not exist"),
    CATEGORY_DOES_NOT_EXIST("400", "Category with Id does not exist"),
    CATEGORY_ALREADY_EXIST("400", "Category already exist"),
    ERROR("400", "Something Went Wrong");

    private final String responseCode;
    private String responseMessage;

    ResponseEnum(String code, String message) {
        this.responseMessage = message;
        this.responseCode = code;
    }

    ResponseEnum(String code) {
        this.responseCode = code;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}
