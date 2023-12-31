package com.yfh.bookstore.ddd.adapter.web;

import java.util.List;


import com.yfh.common.resp.SingleResponse;
import com.yfh.bookstore.ddd.adapter.convertor.CategoryConvertor;
import com.yfh.bookstore.ddd.adapter.dto.CategoryDTO;
import com.yfh.bookstore.ddd.adapter.dto.CategoryDTO.create;
import com.yfh.bookstore.ddd.adapter.vo.CategoryVO;
import com.yfh.bookstore.ddd.infrastructure.mapper.BookCategoryMapper;
import com.yfh.bookstore.ddd.infrastructure.model.BookCategoryDO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;

//@Tag(name = "CategoryInfoController", description = "书籍类别 API")
@RestController
@RequestMapping("/category")
public class CategoryInfoController {
 

    @Autowired
    private BookCategoryMapper bookCategoryMapper;
    
    @Operation(tags = { "ReadInfo" }, summary = "获得所有书籍类别", description = "返回书籍类别列表，展示需要自行实现树结构")
    @ApiResponse(description = "书籍类别列表", useReturnTypeSchema = true)
    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('USER','VIP','LTS','ADMIN')")
    public SingleResponse<List<CategoryVO>> getAllCategories() {

        List<BookCategoryDO> bookCategoryDOList = bookCategoryMapper.selectList(null);
        return SingleResponse.of(CategoryConvertor.toCategoryVOList(bookCategoryDOList));

    }

    @Operation(tags = {"Edition"}, summary = "添加书籍类别", description = "成功返回添加了的信息")
    @Parameter(name = "categoryDTO", description = "类别信息")
    @ApiResponse(description = "成功添加的类别信息", useReturnTypeSchema = true)
    @PostMapping("/addCategory")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ADMIN')")
    public SingleResponse<CategoryVO> addCategory(@RequestBody @Validated(create.class) CategoryDTO categoryDTO){
       
        BookCategoryDO bookCategoryDO = CategoryConvertor.toBookCategoryDO(categoryDTO);
        bookCategoryMapper.insert(bookCategoryDO);
        return SingleResponse.of(CategoryConvertor.toCategoryVO(bookCategoryDO));

    }

   




}
