package com.yfh.bookstore.ddd.adapter.graphql.resolver;

import java.util.List;

import com.yfh.bookstore.ddd.adapter.convertor.BookInfoConvertor;
import com.yfh.bookstore.ddd.adapter.vo.BookInfoVO;
import com.yfh.bookstore.ddd.infrastructure.mapper.BookInfoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class BookResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

   @Autowired
   private BookInfoMapper bookInfoMapper;

   public List<BookInfoVO> queryBookList(){

      return null;
       
   }

   public BookInfoVO queryBookById(Integer id){
      
      return BookInfoConvertor.toBookInfoVO(bookInfoMapper.selectById(id));
   }

   public BookInfoVO updateBook(){
      return null;
   }
    
}
