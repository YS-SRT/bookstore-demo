package com.yfh.bookstore.ddd.adapter.graphql.queryparam;

import lombok.Data;

@Data
public class BookParams {

    private int id;
    private String name;
    private String author;
    private String isbn;
    
}
