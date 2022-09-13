package com.linda.bookibrary.demo.dto.request;

import com.linda.bookibrary.demo.constant.MessageConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class AddBookToFavouriteRequest {

    @NotNull(message = MessageConstant.USER_ID )
    private Long userId;

    @NotNull(message =MessageConstant.BOOK_ID_NULL )
    private Long bookId;
}
