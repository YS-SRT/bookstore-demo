package com.yfh.bsecurity.entity;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank
    @Length(min = 6, max = 20)
    private String loginName;
    
    @NotBlank
    @Length(min = 6, max = 20)
    private String password;

    @NotBlank
    private String grantType = "token";
}
