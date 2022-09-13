package com.linda.bookibrary.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
/**
 * @author linda.agbaka
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    private String responseCode;
    private String responseMessage;
    private long execTime;
    private T data = null;
}
