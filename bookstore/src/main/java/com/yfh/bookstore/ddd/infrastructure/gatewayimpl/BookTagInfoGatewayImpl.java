package com.yfh.bookstore.ddd.infrastructure.gatewayimpl;

import com.yfh.bookstore.ddd.infrastructure.model.BookTagInfoDO;
import com.yfh.bookstore.ddd.infrastructure.mapper.BookTagInfoMapper;
import com.yfh.bookstore.ddd.domain.gateway.BookTagInfoGateway;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图书标签关联表 服务实现类
 * </p>
 *
 * @author yfh
 * @since 2022-05-10 16:45:13
 */
@Service
public class BookTagInfoGatewayImpl extends ServiceImpl<BookTagInfoMapper, BookTagInfoDO> implements BookTagInfoGateway {

}
