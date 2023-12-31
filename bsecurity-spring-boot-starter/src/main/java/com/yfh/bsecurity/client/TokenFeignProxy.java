package com.yfh.bsecurity.client;

import com.yfh.common.dubbo.login.entity.LoginDTO;
import com.yfh.common.dubbo.login.entity.TokenVO;
import com.yfh.common.resp.SingleResponse;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TokenFeignProxy {
    
    @PostMapping("/oauth/login")
    public SingleResponse<TokenVO> login(@RequestBody @Validated LoginDTO loginDTO);
}
