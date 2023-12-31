package com.yfh.bookstore.ddd.adapter.convertor;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yfh.bookstore.ddd.adapter.dto.BookInfoDTO;
import com.yfh.bookstore.ddd.adapter.vo.BookInfoVO;
import com.yfh.bookstore.ddd.infrastructure.model.BookInfoDO;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

public class BookInfoConvertor {

    public static BookInfoVO toBookInfoVO(BookInfoDO bookInfoDO){
        if(bookInfoDO != null){
            BookInfoVO bookInfoVO = BeanUtils.instantiateClass(BookInfoVO.class);
            BeanUtils.copyProperties(bookInfoDO, bookInfoVO);
            return bookInfoVO;
        }

        return null;
    }

    public static List<BookInfoVO> toBookInfoVOList(List<BookInfoDO> bookInfoDOs){
        if(!CollectionUtils.isEmpty(bookInfoDOs)){
           return bookInfoDOs.parallelStream().map(BookInfoConvertor::toBookInfoVO).collect(Collectors.toList());
        }
        return null;
    }

    public static Page<BookInfoVO> toBookInfoVOPage(Page<BookInfoDO> bookInfoDOPage){
        
        if(bookInfoDOPage != null){

            Page<BookInfoVO> bookInfoVOPage = new Page<>(bookInfoDOPage.getCurrent(), bookInfoDOPage.getSize(), true);
            bookInfoVOPage.setRecords(toBookInfoVOList(bookInfoDOPage.getRecords()));
            BeanUtils.copyProperties(bookInfoDOPage, bookInfoVOPage, "records");
            return bookInfoVOPage;
        }

        return null;
    }

    public static BookInfoDO  toBookInfoDO(BookInfoDTO bookInfoDTO){
        if(bookInfoDTO != null){
           BookInfoDO bookInfoDO = BeanUtils.instantiateClass(BookInfoDO.class);
           BeanUtils.copyProperties(bookInfoDTO, bookInfoDO);
           return bookInfoDO;
        }

        return null;
    }



    
}
