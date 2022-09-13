package com.linda.bookibrary.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EditBookRequest {

    private String title;

    private String author;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd-MM-yyyy")
    private LocalDate publishedDate;

    private Long bookCategoryId;
}
