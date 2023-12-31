package com.yfh.bookstore.ddd.infrastructure.gatewayimpl;

import com.yfh.bookstore.ddd.infrastructure.model.BookTagCountDO;
import com.yfh.bookstore.ddd.infrastructure.mapper.BookTagCountMapper;
import com.yfh.bookstore.ddd.domain.gateway.BookTagCountGateway;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图书标签计数表 服务实现类
 * </p>
 *
 * @author yfh
 * @since 2022-05-10 16:45:13
 */
@Service
public class BookTagCountGatewayImpl extends ServiceImpl<BookTagCountMapper, BookTagCountDO> implements BookTagCountGateway {

}
