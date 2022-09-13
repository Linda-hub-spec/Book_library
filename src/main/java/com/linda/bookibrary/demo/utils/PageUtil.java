package com.linda.bookibrary.demo.utils;

import com.linda.bookibrary.demo.dto.response.PageResponseData;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class
PageUtil {
    public static Pageable getPage(int number, int size, Boolean isSorted, String sortBy) {
        Pageable paging;

        size = size<2?10:size;
        sortBy = sortBy==null?"createdDate":sortBy;
            if ((number > 0) && (size>0)) {
                if (isSorted != null && isSorted){
                    paging = PageRequest.of(Integer.valueOf(number - 1), Integer.valueOf(size), Sort.by(Sort.Direction.DESC,"createdDate"));
                    if (sortBy != null){
                        paging = PageRequest.of(Integer.valueOf(number - 1), Integer.valueOf(size), Sort.by(Sort.Direction.DESC,sortBy));
                    }
                }else if(isSorted != null && isSorted == false) {
                    paging = PageRequest.of(Integer.valueOf(number - 1), Integer.valueOf(size),Sort.by(Sort.Direction.ASC,"createdDate"));
                }
                else{
                    paging = PageRequest.of(Integer.valueOf(number - 1), Integer.valueOf(size),Sort.by(Sort.Direction.ASC,"createdDate"));
                    if (sortBy != null){
                        paging = PageRequest.of(Integer.valueOf(number - 1), Integer.valueOf(size), Sort.by(Sort.Direction.DESC,sortBy));
                    }
                }
            } else if ((number < 1) && (size>0)){
                if (isSorted != null && isSorted){
                    paging = PageRequest.of(0, Integer.valueOf(size),Sort.by(Sort.Direction.DESC,"createdDate"));
                    if (sortBy != null){
                        paging = PageRequest.of(Integer.valueOf(number - 1), Integer.valueOf(size), Sort.by(Sort.Direction.DESC,sortBy));
                    }
                }else if(isSorted != null && isSorted == false) {
                    paging = PageRequest.of(Integer.valueOf(number - 1), Integer.valueOf(size),Sort.by(Sort.Direction.ASC,"createdDate"));
                }
                else {
                    paging = PageRequest.of(0, Integer.valueOf(size),Sort.by(Sort.Direction.ASC,"createdDate"));
                    if (sortBy != null){
                        paging = PageRequest.of(Integer.valueOf(number - 1), Integer.valueOf(size), Sort.by(Sort.Direction.DESC,sortBy));
                    }
                }
            }
            else {
                if (isSorted != null && isSorted){
//                    log.info("Got here :::::::::::::::::: Sorted");
                    paging = PageRequest.of(0, 10,Sort.by(Sort.Direction.DESC,"createdDate"));
                    if (sortBy != null){
                        paging = PageRequest.of(Integer.valueOf(number - 1), Integer.valueOf(size), Sort.by(Sort.Direction.DESC,sortBy));
                    }
                }else if(isSorted != null && isSorted == false) {
                    paging = PageRequest.of(Integer.valueOf(number - 1), Integer.valueOf(size),Sort.by(Sort.Direction.ASC,"createdDate"));
                }
                else {
                    paging = PageRequest.of(0, 10,Sort.by(Sort.Direction.ASC,"createdDate"));
                    if (sortBy != null){
                        paging = PageRequest.of(Integer.valueOf(number - 1), Integer.valueOf(size), Sort.by(Sort.Direction.DESC,sortBy));
                    }
                }
            }
            return paging;
    }


    public static PageResponseData getPageStatistics(Integer page, Integer sise, long pageTotal,long totalElement){
        PageResponseData pageResData = new PageResponseData();
        pageResData.setFirst(page == 1 || page == null);

        pageResData.setLast(page == pageTotal);

        pageResData.setSize(sise);
        pageResData.setNumber(page);
        pageResData.setTotalPages(pageTotal);
        pageResData.setTotalElements(totalElement);
        return pageResData;
    }
}
