package com.linda.bookibrary.demo.dto.request;

import com.linda.bookibrary.demo.constant.MessageConstant;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddCategoryRequest {

    @NotEmpty(message = MessageConstant.CATEGORY)
    private String categoryName;
}
