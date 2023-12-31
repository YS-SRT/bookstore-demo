package com.yfh.common.dubbo.login;

import java.lang.Object;
import com.yfh.common.dubbo.login.entity.LoginDTO;
import com.yfh.common.dubbo.login.entity.TokenVO;
import com.yfh.common.resp.SingleResponse;

public interface ILoginService {
   public SingleResponse<TokenVO> login(LoginDTO loginDTO);
   public SingleResponse<TokenVO> refresh(Object verifiedClaim);
}
