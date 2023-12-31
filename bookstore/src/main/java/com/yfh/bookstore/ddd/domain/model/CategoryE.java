package com.yfh.bookstore.ddd.domain.model;

import lombok.Data;

@Data
public class CategoryE {
    private int catId;
    private String name;
    private int parentId;
    private int seq;
    private int level;
}
