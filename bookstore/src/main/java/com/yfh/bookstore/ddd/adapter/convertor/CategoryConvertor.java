package com.yfh.bookstore.ddd.adapter.convertor;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.yfh.bookstore.ddd.adapter.dto.CategoryDTO;
import com.yfh.bookstore.ddd.adapter.vo.CategoryVO;
import com.yfh.bookstore.ddd.infrastructure.model.BookCategoryDO;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

public class CategoryConvertor {


    public static CategoryVO toCategoryVO(BookCategoryDO bookCategoryDO){
       if(bookCategoryDO != null){
           CategoryVO categoryVO = BeanUtils.instantiateClass(CategoryVO.class);
           BeanUtils.copyProperties(bookCategoryDO, categoryVO);
           return categoryVO;
       } 

       return null;

    }

    public static List<CategoryVO> toCategoryVOList(List<BookCategoryDO> bookCategoryDOList){
        if(!CollectionUtils.isEmpty(bookCategoryDOList)){
            return bookCategoryDOList.stream().map(CategoryConvertor::toCategoryVO).collect(Collectors.toList());
        }

        return null;
    }

    public static BookCategoryDO toBookCategoryDO(CategoryDTO categoryDTO){
        if(categoryDTO != null){
            BookCategoryDO bookCategoryDO = BeanUtils.instantiateClass(BookCategoryDO.class);
            BeanUtils.copyProperties(categoryDTO, bookCategoryDO);
            return bookCategoryDO;
        }

        return null;
    }
    
}
