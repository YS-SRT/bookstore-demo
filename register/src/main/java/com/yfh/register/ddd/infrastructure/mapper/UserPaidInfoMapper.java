package com.yfh.register.ddd.infrastructure.mapper;

import com.yfh.register.ddd.infrastructure.model.UserPaidInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户付费信息表 Mapper 接口
 * </p>
 *
 * @author yfh
 * @since 2022-05-06 17:40:59
 */
@Mapper
public interface UserPaidInfoMapper extends BaseMapper<UserPaidInfoDO> {

}
