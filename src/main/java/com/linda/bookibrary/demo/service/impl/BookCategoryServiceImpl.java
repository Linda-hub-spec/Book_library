package com.linda.bookibrary.demo.service.impl;

import com.linda.bookibrary.demo.dto.request.AddCategoryRequest;
import com.linda.bookibrary.demo.dto.response.BaseResponse;

import com.linda.bookibrary.demo.dto.response.PageResponseData;
import com.linda.bookibrary.demo.entity.Book;
import com.linda.bookibrary.demo.entity.BookCategory;
import com.linda.bookibrary.demo.enums.BaseStatus;
import com.linda.bookibrary.demo.enums.ResponseEnum;
import com.linda.bookibrary.demo.repositories.BookCategoryRepository;
import com.linda.bookibrary.demo.service.BookCategoryService;
import com.linda.bookibrary.demo.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    @Autowired
    BookCategoryRepository bookCategoryRepository;

    @Override
    public BaseResponse createCategory(AddCategoryRequest request) {
        Long startTime = System.currentTimeMillis();
        BaseResponse response = new BaseResponse();

        try{
            Optional<BookCategory> bookCategoryOptional = bookCategoryRepository.findByName(request.getCategoryName());
            if (bookCategoryOptional.isPresent()) {
                response.setResponseCode(ResponseEnum.CATEGORY_ALREADY_EXIST.getResponseCode());
                response.setResponseMessage(ResponseEnum.CATEGORY_ALREADY_EXIST.getResponseMessage());
                response.setExecTime(System.currentTimeMillis() - startTime);
                return response;
            }

            BookCategory bookCategory = new BookCategory();
            bookCategory.setName(request.getCategoryName());

            bookCategoryRepository.save(bookCategory);

            response.setResponseCode(ResponseEnum.SUCCESSFUL.getResponseCode());
            response.setResponseMessage(ResponseEnum.SUCCESSFUL.getResponseMessage());
            response.setExecTime(System.currentTimeMillis() - startTime);
            return response;

        }catch(Exception ex){
            response.setResponseCode(ResponseEnum.ERROR.getResponseCode());
            response.setResponseMessage(ResponseEnum.ERROR.getResponseMessage() + " "+ ex.getStackTrace());
            response.setExecTime(System.currentTimeMillis() - startTime);
            return response;
        }

    }

    @Override
    public BaseResponse editCategory(Long bookCategoryId, AddCategoryRequest request) {
        Long startTime = System.currentTimeMillis();
        BaseResponse response = new BaseResponse();

        try {
            Optional<BookCategory> bookCategoryOptional = bookCategoryRepository.findById(bookCategoryId);
            if (!bookCategoryOptional.isPresent()) {
                response.setResponseCode(ResponseEnum.CATEGORY_DOES_NOT_EXIST.getResponseCode());
                response.setResponseMessage(ResponseEnum.CATEGORY_DOES_NOT_EXIST.getResponseMessage());
                response.setExecTime(System.currentTimeMillis() - startTime);
                return response;
            }

            BookCategory bookCategory = new BookCategory();
            bookCategory.setName(request.getCategoryName() == null ? bookCategory.getName() : request.getCategoryName());

            bookCategoryRepository.save(bookCategory);

            response.setResponseCode(ResponseEnum.SUCCESSFUL.getResponseCode());
            response.setResponseMessage(ResponseEnum.SUCCESSFUL.getResponseMessage());
            response.setExecTime(System.currentTimeMillis() - startTime);
            return response;

        }catch(Exception ex){

            response.setResponseCode(ResponseEnum.ERROR.getResponseCode());
            response.setResponseMessage(ResponseEnum.ERROR.getResponseMessage() + " "+ ex.getStackTrace());
            response.setExecTime(System.currentTimeMillis() - startTime);
            return response;
        }

    }

    @Override
    public BaseResponse deleteCategory(Long bookCategoryId) {
        Long startTime = System.currentTimeMillis();
        BaseResponse response = new BaseResponse();

        try {

            Optional<BookCategory> bookCategoryOptional = bookCategoryRepository.findById(bookCategoryId);
            if (!bookCategoryOptional.isPresent()) {
                response.setResponseCode(ResponseEnum.CATEGORY_DOES_NOT_EXIST.getResponseCode());
                response.setResponseMessage(ResponseEnum.CATEGORY_DOES_NOT_EXIST.getResponseMessage());
                response.setExecTime(System.currentTimeMillis() - startTime);
                return response;
            }

            BookCategory bookCategory = new BookCategory();
            bookCategory.setStatus(BaseStatus.DELETED);
            bookCategoryRepository.save(bookCategory);

            response.setResponseCode(ResponseEnum.SUCCESSFUL.getResponseCode());
            response.setResponseMessage(ResponseEnum.SUCCESSFUL.getResponseMessage());
            response.setExecTime(System.currentTimeMillis() - startTime);
            return response;

        }catch(Exception ex){

            response.setResponseCode(ResponseEnum.ERROR.getResponseCode());
            response.setResponseMessage(ResponseEnum.ERROR.getResponseMessage() + " "+ ex.getStackTrace());
            response.setExecTime(System.currentTimeMillis() - startTime);
            return response;
        }

    }

    @Override
    public BaseResponse getCategory(BaseStatus status, Boolean sort, String sortBy, int number, int size) {
        Long startTime = System.currentTimeMillis();
        BaseResponse response = new BaseResponse();

        Pageable paging = PageUtil.getPage(number,size,sort,sortBy);

        Page<BookCategory> bookCategoryPage = bookCategoryRepository.getBookCategory(status,paging);

        List<BookCategory> bookCategories = new ArrayList<>();

        if(bookCategoryPage.hasContent()){
            bookCategories = bookCategoryPage.getContent();
        }
        PageResponseData pageResData = PageUtil.getPageStatistics(bookCategoryPage.getNumber(),bookCategoryPage.getSize(),
                bookCategoryPage.getTotalPages(),bookCategoryPage.getTotalElements());
        pageResData.setContent(bookCategories);

        response.setResponseCode(ResponseEnum.SUCCESSFUL.getResponseCode());
        response.setResponseMessage(ResponseEnum.SUCCESSFUL.getResponseMessage());
        response.setData(pageResData);
        response.setExecTime(System.currentTimeMillis() - startTime);
        return response;
    }
}
