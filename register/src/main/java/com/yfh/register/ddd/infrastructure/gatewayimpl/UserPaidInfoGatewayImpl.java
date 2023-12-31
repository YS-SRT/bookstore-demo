package com.yfh.register.ddd.infrastructure.gatewayimpl;

import com.yfh.register.ddd.infrastructure.model.UserPaidInfoDO;
import com.yfh.register.ddd.infrastructure.mapper.UserPaidInfoMapper;
import com.yfh.register.ddd.domain.gateway.UserPaidInfoGateway;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户付费信息表 服务实现类
 * </p>
 *
 * @author yfh
 * @since 2022-05-06 17:40:59
 */
@Service
public class UserPaidInfoGatewayImpl extends ServiceImpl<UserPaidInfoMapper, UserPaidInfoDO> implements UserPaidInfoGateway {

}
