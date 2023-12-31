package com.yfh.register.ddd.adapter.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import com.alibaba.cola.exception.SysException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yfh.common.resp.SingleResponse;
import com.yfh.common.error.ErrorCode;
import com.yfh.register.ddd.adapter.dto.UserRegisterDTO;
import com.yfh.register.ddd.adapter.searchvo.UserSearchVO;
import com.yfh.register.ddd.adapter.vo.CodeImageVO;
import com.yfh.register.ddd.adapter.vo.converter.UserVOConverter;
import com.yfh.register.ddd.infrastructure.mapper.UserInfoMapper;
import com.yfh.register.ddd.infrastructure.model.UserInfoDO;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="RegisterController", description = "开放用户注册页面")
@RestController
@RequestMapping("/oauth")
public class RegisterController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RandomGenerator codeGenerator;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Operation(summary = "获取图片和验证码", description = "前端检验机器人的验证")
    @GetMapping("/getCodeImg")
    public SingleResponse<CodeImageVO> getCodeImg(){
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(70, 30, 4, 4);
        captcha.setGenerator(codeGenerator);
        captcha.createCode();
        //CodeImageVO
        CodeImageVO ci = new CodeImageVO();
        ci.setCode(captcha.getCode());
        ci.setImgBase64Str(captcha.getImageBase64Data());
        
        return SingleResponse.of(ci);
    }


    @Operation(summary = "用户注册", description = "返回相关信息")
    @Parameter(name="registerDTO", description = "用户注册信息")
    @ApiResponse(description = "返回用户信息和生成的Id", useReturnTypeSchema = true)
    @PostMapping("/register")
    public SingleResponse<UserSearchVO> register(@RequestBody @Validated(UserRegisterDTO.create.class) UserRegisterDTO registerDTO){
        //check unique loginName
        QueryWrapper<UserInfoDO> qm = new QueryWrapper<>();
        Long existedCount = userInfoMapper.selectCount(qm.eq("login_name", registerDTO.getLoginName()));
        if(existedCount < 1){

           UserInfoDO userInfoDO = UserVOConverter.toUserInfoDO(registerDTO);
           userInfoDO.setPassword(passwordEncoder.encode(userInfoDO.getPassword()));
           userInfoDO.setUserType(0);
           userInfoDO.setLoginIp(request.getRemoteAddr());
           userInfoMapper.insert(userInfoDO);
           return SingleResponse.of(UserVOConverter.toUserSearchVO(userInfoDO));

        }

        throw new SysException(ErrorCode.ERROR_CODE_EXISTED_LOGIN_NAME_FAILED, "存在相同的登录名") ;
    } 
    
    

}
