package com.yfh.bsecurity.controller;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.yfh.common.resp.SingleResponse;
import com.yfh.common.dubbo.login.ILoginService;
import com.yfh.common.dubbo.login.entity.LoginDTO;
import com.yfh.common.dubbo.login.entity.TokenVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import com.yfh.bsecurity.mapper.BSUserDetailsMapper;
import com.yfh.bsecurity.config.BSecurityTokenProperties;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.scheduling.annotation.EnableAsync;

import com.yfh.bsecurity.service.LoginService;
import com.yfh.bsecurity.security.AuthorityGettingHelper;

@RestController
@EnableAsync
public class BSecurityTokenController implements AuthorityGettingHelper{

    
    @Resource
    private BSecurityTokenProperties tokenProperties;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private BSUserDetailsMapper userDetailsMapper;

    @Resource
    private LoginService loginService;
    
    
    @PostMapping("/oauth/login")
    public SingleResponse<TokenVO> login(@RequestBody @Validated LoginDTO loginDTO) throws Exception{
        SingleResponse<TokenVO> result = loginService.login(loginDTO);
        // async update in db
        if(!"proxy".equalsIgnoreCase(tokenProperties.getMode()) && result.getData()!= null){
            
           updateLoginStatus(result.getData().getId(), request.getRemoteAddr());
        }
        return result;
          
    }



    @PreAuthorize("hasAnyRole('USER','VIP','LTS','ADMIN')")
    @PostMapping("/auth/refresh_token")
    public SingleResponse<TokenVO> refresh(){

       return loginService.refresh(Objects.requireNonNull(getVerifiedClaim(request)));
    }

    @Async
    private void updateLoginStatus(int userId, String loginIp) {
        userDetailsMapper.updateLoginInfoById(loginIp, LocalDateTime.now(), userId);
    }
       
}
