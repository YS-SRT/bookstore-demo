package com.yfh.bookstore.ddd.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class BookE {

    private int bookId;
    private String isdn;
    private String name;
    private String author;
    private Date publishDate;
    private int catId;
    
    private Date inputDate;
}
