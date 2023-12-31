package com.yfh.bsecurity.service;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.yfh.bsecurity.client.DynamicDubboBuilder;
import com.yfh.bsecurity.client.DynamicFeignBuilder;
import com.yfh.bsecurity.client.TokenFeignProxy;
import com.yfh.bsecurity.config.BSecurityTokenProperties;
import com.yfh.bsecurity.mapper.BSUserDetailsMapper;
import com.yfh.bsecurity.security.AuthorityGettingHelper;
import com.yfh.bsecurity.security.BSUserDetails;
import com.yfh.bsecurity.token.JwtTokenService;
import com.yfh.common.resp.SingleResponse;
import com.yfh.common.dubbo.login.ILoginService;
import com.yfh.common.dubbo.login.entity.LoginDTO;
import com.yfh.common.dubbo.login.entity.TokenVO;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Resource
    private BSecurityTokenProperties tokenProperties;

    @Resource
    private JwtTokenService jwtTokenService;

    @Autowired(required = false)
    private DynamicFeignBuilder feignBuilder;

    @Autowired
    private BSUserDetailsMapper userDetailsMapper;

    @Autowired(required = false)
    private DynamicDubboBuilder dubboBuilder;

    public SingleResponse<TokenVO> login(LoginDTO loginDTO) throws Exception{

        //dubbo first
        if ("proxy".equalsIgnoreCase(tokenProperties.getMode()) && dubboBuilder != null) {
            return dubboBuilder.getInstance().login(loginDTO);
        }

        //http second
        if ("proxy".equalsIgnoreCase(tokenProperties.getMode()) && feignBuilder != null) {

            return feignBuilder.getFeignClient(TokenFeignProxy.class, tokenProperties.getProxyTargetServiceName())
                    .login(loginDTO);
        }

        //localDB last
        UsernamePasswordAuthenticationToken usernamePasswordToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getLoginName(), loginDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordToken);
        if (Objects.isNull(authentication)) {

            return SingleResponse.of(null);

        } else {

            BSUserDetails userDetails = (BSUserDetails) authentication.getPrincipal();
            String token = jwtTokenService.createToken(userDetails.getUserId(), userDetails.getUserType(),
                    userDetails.getUsername(), tokenProperties.getAccessExpirationSeconds());
            String refreshToken = jwtTokenService.createToken(userDetails.getUserId(), userDetails.getUserType(),
                    userDetails.getUsername(), tokenProperties.getRefreshExpirationSeconds());
            TokenVO tokenVO = BeanUtils.instantiateClass(TokenVO.class);
            tokenVO.setId(userDetails.getUserId());
            tokenVO.setAccessToken(token);
            tokenVO.setRefreshToken(refreshToken);
            tokenVO.setExpiresIn(tokenProperties.getAccessExpirationSeconds());
            return SingleResponse.of(tokenVO);
        }

    }

    public SingleResponse<TokenVO> refresh(Object verifiedClaim){
      
       List<String> newTokenList = jwtTokenService.refreshToken(verifiedClaim);
       if(newTokenList != null){
          TokenVO tokenVO = BeanUtils.instantiateClass(TokenVO.class);
          tokenVO.setAccessToken(newTokenList.get(0));
          tokenVO.setRefreshToken(newTokenList.get(1));
          tokenVO.setExpiresIn(tokenProperties.getAccessExpirationSeconds());
       
          return SingleResponse.of(tokenVO);
       }

       return SingleResponse.of(null);
    }

    
}
