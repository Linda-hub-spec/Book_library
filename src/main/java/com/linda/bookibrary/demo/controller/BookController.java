package com.linda.bookibrary.demo.controller;

import com.linda.bookibrary.demo.dto.request.AddBookToFavouriteRequest;
import com.linda.bookibrary.demo.dto.request.AddBooksToCategoryRequest;
import com.linda.bookibrary.demo.dto.response.BaseResponse;
import com.linda.bookibrary.demo.dto.request.AddBookRequest;
import com.linda.bookibrary.demo.dto.request.EditBookRequest;
import com.linda.bookibrary.demo.enums.BaseStatus;
import com.linda.bookibrary.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author linda.agbaka
 */
@RestController
@CrossOrigin
@RequestMapping("/api/library")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<?> addBook(@RequestBody @Valid AddBookRequest request){
        BaseResponse response = bookService.addBook(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/book/{bookId}")
    public ResponseEntity<?> editBook(@PathVariable Long bookId, @RequestBody @Valid EditBookRequest request){

        BaseResponse response = bookService.editBook(bookId,request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId){

        BaseResponse response = bookService.deleteBook(bookId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/books")
    public ResponseEntity<?> listBook(@RequestParam(required = false) BaseStatus status,
                                      @RequestParam(required = false) Boolean sort,
                                      @RequestParam(required = false) String sortBy,
                                      @RequestParam(required = false,defaultValue = "1") int number,
                                      @RequestParam(required = false,defaultValue = "0") int size){
        BaseResponse response = bookService.getBooks(status,sort,sortBy,number,size);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/book/category")
    public ResponseEntity<?> addBookAsFavourite(@RequestBody @Valid AddBooksToCategoryRequest request) {
        BaseResponse response = bookService.addBookToCategory(request);
        return ResponseEntity.ok().body(response);

    }

    @PutMapping( "/addCategory")
    public ResponseEntity<?> addBookAsFavourite(@RequestBody @Valid AddBookToFavouriteRequest request) {
        BaseResponse response = bookService.addBookToFavorite(request);
        return ResponseEntity.ok().body(response);
    }

}
