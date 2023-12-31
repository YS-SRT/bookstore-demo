package com.yfh.bookstore.ddd.infrastructure.mapper;

import com.yfh.bookstore.ddd.infrastructure.model.BookTagCountDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 图书标签计数表 Mapper 接口
 * </p>
 *
 * @author yfh
 * @since 2022-05-10 16:45:13
 */
@Mapper
public interface BookTagCountMapper extends BaseMapper<BookTagCountDO> {

}
