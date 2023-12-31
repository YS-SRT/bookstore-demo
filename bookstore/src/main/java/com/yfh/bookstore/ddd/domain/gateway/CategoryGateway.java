package com.yfh.bookstore.ddd.domain.gateway;

import java.util.List;

import com.yfh.bookstore.ddd.domain.model.CategoryE;

public interface CategoryGateway {
    CategoryE getById(int catId);
    List<CategoryE> getByLevel(int level);
}
