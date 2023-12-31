package com.yfh.register.ddd.adapter.vo;

import lombok.Data;

@Data
public class TokenVO {
    private String accessToken;
    private String refreshToken;
    private long expiresIn; 
}
