package com.yfh.register.ddd.infrastructure.gatewayimpl;

import com.yfh.register.ddd.infrastructure.model.UserInfoDO;
import com.yfh.register.ddd.infrastructure.mapper.UserInfoMapper;
import com.yfh.register.ddd.domain.gateway.UserInfoGateway;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author yfh
 * @since 2022-05-06 17:40:58
 */
@Service
public class UserInfoGatewayImpl extends ServiceImpl<UserInfoMapper, UserInfoDO> implements UserInfoGateway {

}
