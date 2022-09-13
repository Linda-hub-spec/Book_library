package com.linda.bookibrary.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linda.bookibrary.demo.constant.MessageConstant;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class AddBookRequest {

    @NotEmpty(message = MessageConstant.BOOK_TITLE)
    private String title;

    @NotEmpty(message =MessageConstant.BOOK_AUTHOR )
    private String author;

    @NotNull(message = MessageConstant.PUBLISHED_DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd-MM-yyyy")
    private LocalDate publishedDate;


}
