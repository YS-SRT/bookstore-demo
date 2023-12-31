package com.yfh.bookstore.ddd.domain.gateway;

import com.yfh.bookstore.ddd.domain.model.BookE;

public interface BookGateway {
   BookE getById(int bookId);
   
   BookE getByISDN(String ISDN);

}
