package com.yfh.common.dubbo.login.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class TokenVO implements Serializable{

    private static final long serialVersionUID = 1L;

    private int id;
    private String accessToken;
    private String refreshToken;
    private int expiresIn;
}
