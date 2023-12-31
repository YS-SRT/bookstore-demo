package com.yfh.bsecurity.dubbo;

import java.lang.Object;
import java.util.Objects;
import java.lang.Override;
import javax.annotation.Resource;
import com.yfh.common.dubbo.login.entity.LoginDTO;
import com.yfh.common.dubbo.login.entity.TokenVO;
import com.yfh.common.error.ErrorCode;
import com.yfh.common.dubbo.login.ILoginService;
import com.yfh.common.resp.SingleResponse;
import org.apache.dubbo.config.annotation.DubboService;
import com.yfh.bsecurity.service.LoginService;

@DubboService(interfaceClass = ILoginService.class)
public class LoginServiceImpl implements ILoginService{

    @Resource
    private LoginService loginService;

    @Override
    public SingleResponse<TokenVO> login(LoginDTO loginDTO) {
        SingleResponse<TokenVO> result;

        try{
             result = loginService.login(loginDTO);

        }catch(Exception ex){

           result = SingleResponse.buildFailure(ErrorCode.ERROR_CODE_BUSINESS_GENERAL_FAILED, ex.getMessage());
        }

        return result;
    }

    @Override
    public SingleResponse<TokenVO> refresh(Object verifiedClaim){
        
        return loginService.refresh(Objects.requireNonNull(verifiedClaim));
    }

   
}
