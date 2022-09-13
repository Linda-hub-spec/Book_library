package com.linda.bookibrary.demo.service;

import com.linda.bookibrary.demo.dto.request.AddBookToFavouriteRequest;
import com.linda.bookibrary.demo.dto.request.AddBooksToCategoryRequest;
import com.linda.bookibrary.demo.dto.response.BaseResponse;
import com.linda.bookibrary.demo.dto.request.AddBookRequest;
import com.linda.bookibrary.demo.dto.request.EditBookRequest;
import com.linda.bookibrary.demo.enums.BaseStatus;


public interface BookService {
    BaseResponse addBook(AddBookRequest request);

    BaseResponse editBook(Long bookId, EditBookRequest request);

    BaseResponse deleteBook(Long bookId);

    BaseResponse getBooks(BaseStatus status, Boolean sort, String sortBy, int number,int size);

    BaseResponse addBookToCategory(AddBooksToCategoryRequest request);

    BaseResponse addBookToFavorite(AddBookToFavouriteRequest request);

}
