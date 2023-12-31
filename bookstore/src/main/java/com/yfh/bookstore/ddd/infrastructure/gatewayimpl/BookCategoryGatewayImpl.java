package com.yfh.bookstore.ddd.infrastructure.gatewayimpl;

import com.yfh.bookstore.ddd.infrastructure.model.BookCategoryDO;
import com.yfh.bookstore.ddd.infrastructure.mapper.BookCategoryMapper;
import com.yfh.bookstore.ddd.domain.gateway.BookCategoryGateway;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图书类型表 服务实现类
 * </p>
 *
 * @author yfh
 * @since 2022-05-10 16:45:12
 */
@Service
public class BookCategoryGatewayImpl extends ServiceImpl<BookCategoryMapper, BookCategoryDO> implements BookCategoryGateway {

}
