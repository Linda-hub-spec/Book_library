package com.linda.bookibrary.demo.service.impl;

import com.linda.bookibrary.demo.dto.request.AddBookToFavouriteRequest;
import com.linda.bookibrary.demo.dto.request.AddBooksToCategoryRequest;
import com.linda.bookibrary.demo.dto.response.BaseResponse;
import com.linda.bookibrary.demo.dto.request.AddBookRequest;
import com.linda.bookibrary.demo.dto.request.EditBookRequest;
import com.linda.bookibrary.demo.dto.response.PageResponseData;
import com.linda.bookibrary.demo.entity.Book;
import com.linda.bookibrary.demo.entity.BookCategory;
import com.linda.bookibrary.demo.entity.FavouriteBook;
import com.linda.bookibrary.demo.entity.User;
import com.linda.bookibrary.demo.enums.BaseStatus;
import com.linda.bookibrary.demo.enums.ResponseEnum;
import com.linda.bookibrary.demo.repositories.BookCategoryRepository;
import com.linda.bookibrary.demo.repositories.BookRepository;
import com.linda.bookibrary.demo.repositories.FavouriteBookRepository;
import com.linda.bookibrary.demo.repositories.UserRepository;
import com.linda.bookibrary.demo.service.BookService;
import com.linda.bookibrary.demo.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookCategoryRepository bookCategoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FavouriteBookRepository favouriteBookRepository;



    @Override
    public BaseResponse addBook(AddBookRequest request) {

        Long startTime = System.currentTimeMillis();
        BaseResponse response = new BaseResponse();
        try{
            Book book = new Book();
            book.setAuthor(request.getAuthor());
            book.setTitle(request.getTitle());
            book.setPublishedDate(request.getPublishedDate());
            bookRepository.save(book);

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
    public BaseResponse editBook(Long bookId, EditBookRequest request) {
        Long startTime = System.currentTimeMillis();
        BaseResponse response = new BaseResponse();

        try {
            Optional<Book> bookOptional = bookRepository.findById(bookId);
            if (!bookOptional.isPresent()) {
                response.setResponseCode(ResponseEnum.BOOK_DOES_NOT_EXIST.getResponseCode());
                response.setResponseMessage(ResponseEnum.BOOK_DOES_NOT_EXIST.getResponseMessage());
                response.setExecTime(System.currentTimeMillis() - startTime);
                return response;
            }

            Book book = bookOptional.get();
            book.setTitle(request.getTitle() == null ? book.getTitle() : request.getTitle());
            book.setAuthor(request.getAuthor() == null ? book.getAuthor() : request.getAuthor());
            book.setPublishedDate(request.getPublishedDate() == null ? book.getPublishedDate() : request.getPublishedDate());
            bookRepository.save(book);

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
    public BaseResponse deleteBook(Long bookId) {

        Long startTime = System.currentTimeMillis();
        BaseResponse response = new BaseResponse();

        try {

            Optional<Book> bookOptional = bookRepository.findById(bookId);
            if (!bookOptional.isPresent()) {
                response.setResponseCode(ResponseEnum.BOOK_DOES_NOT_EXIST.getResponseCode());
                response.setResponseMessage(ResponseEnum.BOOK_DOES_NOT_EXIST.getResponseMessage());
                response.setExecTime(System.currentTimeMillis() - startTime);
                return response;
            }
            Book book = bookOptional.get();
            book.setStatus(BaseStatus.DELETED);
            bookRepository.save(book);

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
    public BaseResponse getBooks(BaseStatus status, Boolean sort, String sortBy, int number, int size) {

        Long startTime = System.currentTimeMillis();
        BaseResponse response = new BaseResponse();

        Pageable paging = PageUtil.getPage(number,size,sort,sortBy);

        Page<Book> bookPage = bookRepository.getBooks(status,paging);

        List<Book> books = new ArrayList<>();

        if(bookPage.hasContent()){
            books = bookPage.getContent();
        }
        PageResponseData pageResData = PageUtil.getPageStatistics(bookPage.getNumber(),bookPage.getSize(),
                bookPage.getTotalPages(),bookPage.getTotalElements());
        pageResData.setContent(books);

        response.setResponseCode(ResponseEnum.SUCCESSFUL.getResponseCode());
        response.setResponseMessage(ResponseEnum.SUCCESSFUL.getResponseMessage());
        response.setData(pageResData);
        response.setExecTime(System.currentTimeMillis() - startTime);
        return response;
    }

    @Override
    public BaseResponse addBookToCategory(AddBooksToCategoryRequest request) {

        Long startTime = System.currentTimeMillis();
        BaseResponse response = new BaseResponse();
        try {
            Optional<Book> bookOptional = bookRepository.findById(request.getBookId());
            if (!bookOptional.isPresent()) {
                response.setResponseCode(ResponseEnum.BOOK_DOES_NOT_EXIST.getResponseCode());
                response.setResponseMessage(ResponseEnum.BOOK_DOES_NOT_EXIST.getResponseMessage());
                response.setExecTime(System.currentTimeMillis() - startTime);
                return response;
            }
            Optional<BookCategory> bookCategoryOptional = bookCategoryRepository.findById(request.getCategoryId());
            if (!bookCategoryOptional.isPresent()) {
                response.setResponseCode(ResponseEnum.CATEGORY_DOES_NOT_EXIST.getResponseCode());
                response.setResponseMessage(ResponseEnum.CATEGORY_DOES_NOT_EXIST.getResponseMessage());
                response.setExecTime(System.currentTimeMillis() - startTime);
                return response;
            }
            BookCategory bookCategory = bookCategoryOptional.get();
            Book book = bookOptional.get();
            book.setBookCategory(bookCategory);
            bookRepository.save(book);

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
    public BaseResponse addBookToFavorite(AddBookToFavouriteRequest request) {
        Long startTime = System.currentTimeMillis();
        BaseResponse response = new BaseResponse();

        try {

            Optional<Book> bookOptional = bookRepository.findById(request.getBookId());
            if (!bookOptional.isPresent()) {
                response.setResponseCode(ResponseEnum.BOOK_DOES_NOT_EXIST.getResponseCode());
                response.setResponseMessage(ResponseEnum.BOOK_DOES_NOT_EXIST.getResponseMessage());
                response.setExecTime(System.currentTimeMillis() - startTime);
                return response;
            }

            Optional<User> userOptional = userRepository.findById(request.getUserId());
            if (!userOptional.isPresent()) {
                response.setResponseCode(ResponseEnum.USER_DOES_NOT_EXIST.getResponseCode());
                response.setResponseMessage(ResponseEnum.USER_DOES_NOT_EXIST.getResponseMessage());
                response.setExecTime(System.currentTimeMillis() - startTime);
                return response;
            }
            FavouriteBook favouriteBook = new FavouriteBook();
            favouriteBook.setBook(bookOptional.get());
            favouriteBook.setUser(userOptional.get());
            favouriteBookRepository.save(favouriteBook);

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


}
