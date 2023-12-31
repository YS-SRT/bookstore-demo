package com.yfh.register.dubbo;

import com.alibaba.cola.dto.SingleResponse;
import com.yfh.register.ddd.adapter.dto.UserRegisterDTO;
import com.yfh.register.ddd.adapter.searchvo.UserSearchVO;
import com.yfh.register.ddd.adapter.vo.CodeImageVO;

public interface IRegisterService {

    public SingleResponse<CodeImageVO> getCodeImg();
    public SingleResponse<UserSearchVO> register(UserRegisterDTO registerDTO);
    
}
