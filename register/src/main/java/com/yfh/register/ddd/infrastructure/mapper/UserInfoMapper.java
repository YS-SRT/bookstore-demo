package com.yfh.register.ddd.infrastructure.mapper;

import com.yfh.register.ddd.infrastructure.model.UserInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author yfh
 * @since 2022-05-06 17:40:58
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoDO> {

}
