package com.yfh.bookstore.ddd.infrastructure.gatewayimpl;

import com.yfh.bookstore.ddd.infrastructure.model.BookCategoryCountDO;
import com.yfh.bookstore.ddd.infrastructure.mapper.BookCategoryCountMapper;
import com.yfh.bookstore.ddd.domain.gateway.BookCategoryCountGateway;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图书类别计数表 服务实现类
 * </p>
 *
 * @author yfh
 * @since 2022-05-10 16:45:13
 */
@Service
public class BookCategoryCountGatewayImpl extends ServiceImpl<BookCategoryCountMapper, BookCategoryCountDO> implements BookCategoryCountGateway {

}
