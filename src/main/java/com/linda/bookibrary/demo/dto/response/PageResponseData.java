package com.linda.bookibrary.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseData {

    private long number;
    private long size;
    private long totalElements;
    private long totalPages;
    private boolean last;
    private boolean first;
    private List<?> content;
}