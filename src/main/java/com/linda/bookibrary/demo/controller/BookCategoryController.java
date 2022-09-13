package com.linda.bookibrary.demo.controller;

import com.linda.bookibrary.demo.dto.request.AddCategoryRequest;
import com.linda.bookibrary.demo.dto.response.BaseResponse;
import com.linda.bookibrary.demo.enums.BaseStatus;
import com.linda.bookibrary.demo.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author linda.agbaka
 */
@RestController
@CrossOrigin
@RequestMapping("/api/library")
public class BookCategoryController {

    @Autowired
    BookCategoryService bookCategoryService;

    @PostMapping("/category")
    public ResponseEntity<?> addCategory(@RequestBody @Valid AddCategoryRequest request){
        BaseResponse response = bookCategoryService.createCategory(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<?> editCategory(@PathVariable Long categoryId, @RequestBody @Valid AddCategoryRequest request){

        BaseResponse response = bookCategoryService.editCategory(categoryId,request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId){

        BaseResponse response = bookCategoryService.deleteCategory(categoryId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/categories")
    public ResponseEntity<?> listCategory(@RequestParam(required = false) BaseStatus status,
                                      @RequestParam(required = false) Boolean sort,
                                      @RequestParam(required = false) String sortBy,
                                      @RequestParam(required = false,defaultValue = "1") int number,
                                      @RequestParam(required = false,defaultValue = "0") int size){
        BaseResponse response = bookCategoryService.getCategory(status,sort,sortBy,number,size);
        return ResponseEntity.ok().body(response);
    }



}
