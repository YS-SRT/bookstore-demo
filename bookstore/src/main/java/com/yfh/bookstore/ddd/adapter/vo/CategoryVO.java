package com.yfh.bookstore.ddd.adapter.vo;

import lombok.Data;

@Data
public class CategoryVO {


    private Integer id;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 顺序
     */
    private Integer seq;

    /**
     * 层级
     */
    private Integer level;
    
}
