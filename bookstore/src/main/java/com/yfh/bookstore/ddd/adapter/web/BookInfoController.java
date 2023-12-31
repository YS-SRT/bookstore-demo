package com.yfh.bookstore.ddd.adapter.web;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.yfh.common.resp.SingleResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yfh.bookstore.ddd.adapter.convertor.BookInfoConvertor;
import com.yfh.bookstore.ddd.adapter.dto.BookInfoDTO;
import com.yfh.bookstore.ddd.adapter.dto.BookInfoDTO.create;
import com.yfh.bookstore.ddd.adapter.vo.BookInfoVO;
import com.yfh.bookstore.ddd.infrastructure.mapper.BookInfoMapper;
import com.yfh.bookstore.ddd.infrastructure.model.BookInfoDO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;

//@Tag(name="BookInfoController", description = "书籍 API")
@RestController
@RequestMapping("/book")
public class BookInfoController {

    @Autowired
    private BookInfoMapper bookInfoMapper;
    

    @Operation(tags = {"ReadInfo"}, summary = "根据ISDN获取书籍信息", description = "返回书籍详细信息")
    @Parameter(name = "isdn", description = "书籍ISDN")
    @ApiResponse(description = "书籍信息", useReturnTypeSchema = true)
    @GetMapping("/getByISDN")
    @PreAuthorize("hasAnyRole('USER','VIP','LTS','ADMIN')")
    public SingleResponse<BookInfoVO> getByISDN(@RequestParam @Validated @NotBlank String isdn){

        QueryWrapper<BookInfoDO> qw = new QueryWrapper<BookInfoDO>();
        BookInfoDO bookInfoDO = bookInfoMapper.selectOne(qw.eq("isdn", isdn));
        return SingleResponse.of(BookInfoConvertor.toBookInfoVO(bookInfoDO));
        
    }

    @Operation(tags = {"ReadInfo"}, summary = "根据ID获取书籍信息", description = "返回书籍详细信息")
    @Parameter(name = "id", description = "书籍ID")
    @ApiResponse(description = "书籍信息", useReturnTypeSchema = true)
    @GetMapping("/getById")
    @PreAuthorize("hasAnyRole('USER','VIP','LTS','ADMIN')")
    public SingleResponse<BookInfoVO> getById(@RequestParam @Validated @Min(1) int bookId){

        BookInfoDO bookInfoDO = bookInfoMapper.selectById(bookId);
        return SingleResponse.of(BookInfoConvertor.toBookInfoVO(bookInfoDO));
        
    }

    @Operation(tags = {"ReadInfo"}, summary = "分页获取书籍信息", description = "返回书籍详细信息的页面")
    @Parameters({
        @Parameter(name = "pageIndex", description = "当前页索引,最小1,最大10"),
        @Parameter(name = "pageSize", description = "每页所含条目数,最小1,最大10")
    })
    @ApiResponse(description = "书籍信息分页", useReturnTypeSchema = true)
    @GetMapping("/getByPage")
    @PreAuthorize("hasAnyRole('USER','VIP','LTS','ADMIN')")
    public SingleResponse<Page<BookInfoVO>> getBookByPage(@RequestParam @Validated @Size(min=1,max=10) int pageIndex ,@RequestParam @Validated @Size(min=1,max=20) int pageSize){
        
        Page<BookInfoDO> bookInfoDOPage = new Page<>(pageIndex, pageSize, true);
        bookInfoMapper.selectPage(bookInfoDOPage, null);
        return SingleResponse.of(BookInfoConvertor.toBookInfoVOPage(bookInfoDOPage));
    }

    @Operation(tags = { "Edition" }, summary = "添加书籍", description = "成功返回添加了的信息")
    @Parameter(name = "bookInfoDTO", description = "书籍信息")
    @ApiResponse(description = "成功添加的书籍信息", useReturnTypeSchema = true)
    @PostMapping("/addBook")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ADMIN')")
    public SingleResponse<BookInfoVO> addBook(@RequestBody @Validated(create.class) BookInfoDTO bookInfoDTO) {
        BookInfoDO bookInfoDO = BookInfoConvertor.toBookInfoDO(bookInfoDTO);
        bookInfoMapper.insert(bookInfoDO);
        return SingleResponse.of(BookInfoConvertor.toBookInfoVO(bookInfoDO));
    }
    
}
