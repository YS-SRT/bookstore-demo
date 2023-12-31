package com.yfh.common.dubbo.login.entity;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class LoginDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Length(min = 6, max = 20)
    private String loginName;
    
    @NotBlank
    @Length(min = 6, max = 20)
    private String password;

    @NotBlank
    private String grantType = "token";
}
