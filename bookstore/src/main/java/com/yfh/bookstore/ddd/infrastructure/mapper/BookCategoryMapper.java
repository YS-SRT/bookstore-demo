package com.yfh.bookstore.ddd.infrastructure.mapper;

import com.yfh.bookstore.ddd.infrastructure.model.BookCategoryDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 图书类型表 Mapper 接口
 * </p>
 *
 * @author yfh
 * @since 2022-05-10 16:45:12
 */
@Mapper
public interface BookCategoryMapper extends BaseMapper<BookCategoryDO> {

}
