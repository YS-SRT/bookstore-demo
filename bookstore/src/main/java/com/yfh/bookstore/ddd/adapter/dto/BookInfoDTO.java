package com.yfh.bookstore.ddd.adapter.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import com.alibaba.cola.dto.DTO;
import lombok.Data;

@Data
public class BookInfoDTO extends DTO {

    @Positive(groups = update.class)
    @Null(groups = create.class)
    private Integer id;
    
    @Positive(groups = create.class)
    private Integer categoryId;
    private String categoryName;

    @NotBlank(groups = create.class)
    private String name;
    private String author;
    
    private String isbn;
    private String tags;
    private LocalDateTime publishTime;
    private String brief;

    public interface create {};
    public interface update {};
}
