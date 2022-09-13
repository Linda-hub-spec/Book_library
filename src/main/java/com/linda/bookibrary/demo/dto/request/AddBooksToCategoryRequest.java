package com.linda.bookibrary.demo.dto.request;

import com.linda.bookibrary.demo.constant.MessageConstant;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
public class AddBooksToCategoryRequest {

    @NotNull(message = MessageConstant.CATEGORY_ID)
    private Long categoryId;

    @NotNull(message =MessageConstant.BOOK_ID_NULL )
    private Long bookId;
}
