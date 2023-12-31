package com.yfh.bookstore.ddd.infrastructure.gatewayimpl;

import com.yfh.bookstore.ddd.infrastructure.model.BookInfoDO;
import com.yfh.bookstore.ddd.infrastructure.mapper.BookInfoMapper;
import com.yfh.bookstore.ddd.domain.gateway.BookInfoGateway;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图书信息表 服务实现类
 * </p>
 *
 * @author yfh
 * @since 2022-05-10 16:45:13
 */
@Service
public class BookInfoGatewayImpl extends ServiceImpl<BookInfoMapper, BookInfoDO> implements BookInfoGateway {

}
