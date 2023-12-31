package com.yfh.bookstore.ddd.adapter.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoryDTO {

    @Positive(groups = update.class)
    private Integer id;

    /**
     * 父id
     */
    @PositiveOrZero
    private Integer parentId;

    /**
     * 名称
     */
    @NotBlank(groups = create.class)
    @Size(min=2, max=10, groups = create.class)
    private String name;

    /**
     * 顺序
     */
    @PositiveOrZero
    private Integer seq;

    /**
     * 层级
     */
    @Positive
    private Integer level;

    public interface create {};
    public interface update {};

}
