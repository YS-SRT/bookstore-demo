package com.yfh.bookstore.ddd.adapter.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookInfoVO {
    
    /**
     * 编号
     */
    private Integer id;

    /**
     * 类别id
     */
    private Integer categoryId;

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * ISBN编号
     */
    private String isbn;

    /**
     * 标签，格式：#tag1;#tag2
     */
    private String tags;

    /**
     * 出版时间
     */
    private LocalDateTime publishTime;

    /**
     * 简介
     */
    private String brief;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
