package com.yfh.register.ddd.adapter.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
//@Schema(name="UserLoginDTO", description = "登录时提供的信息对象")
public class UserLoginDTO {
    
    @NotBlank(groups = refresh.class)
    //@Schema(name="signedId", description = "用户id,刷新Token时必填,登录时无需填写")
    private String signedId;
    //@Schema(name = "signedSecret", description = "用户secret,预留给第三方验证")
    private String signedSecret;
    
    @NotBlank(groups=get.class)
    @Length(min=6,max=20,groups=get.class)
    //@Schema(name = "loginName", description = "用户登录名,长度为6~20; 登录时必填,刷新Token时无需填写")
    private String loginName;

    @NotBlank(groups = get.class)
    @Length(min = 6, max = 20, groups = get.class)
    //@Schema(name = "password", description = "用户密码,长度为6~20; 登录时必填,刷新Token时无需填写")
    private String password;

    @NotBlank(groups={get.class, refresh.class})
    //@Schema(name = "grantType", description = "授权类型,登录时为token,刷新时为refresh_token")
    private String grantType;

    //@Schema(name = "scope", description = "授权范围,预留")
    private String scope;

    public interface get{};
    public interface refresh{};

}
