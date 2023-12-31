package com.yfh.bookstore.ddd.infrastructure.gatewayimpl;

import com.yfh.bookstore.ddd.infrastructure.model.BookTagDO;
import com.yfh.bookstore.ddd.infrastructure.mapper.BookTagMapper;
import com.yfh.bookstore.ddd.domain.gateway.BookTagGateway;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图书标签表 服务实现类
 * </p>
 *
 * @author yfh
 * @since 2022-05-10 16:45:13
 */
@Service
public class BookTagGatewayImpl extends ServiceImpl<BookTagMapper, BookTagDO> implements BookTagGateway {

}
