package com.linda.bookibrary.demo.service;

import com.linda.bookibrary.demo.dto.request.AddCategoryRequest;
import com.linda.bookibrary.demo.dto.response.BaseResponse;
import com.linda.bookibrary.demo.enums.BaseStatus;

public interface BookCategoryService {

    BaseResponse createCategory(AddCategoryRequest request);

    BaseResponse editCategory(Long bookCategoryId, AddCategoryRequest request);

    BaseResponse deleteCategory(Long bookCategoryId);

    BaseResponse getCategory(BaseStatus status, Boolean sort, String sortBy, int number, int size);

}
