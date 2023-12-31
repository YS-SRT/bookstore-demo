package com.yfh.bsecurity.entity;

import lombok.Data;

@Data
public class TokenVO {
    private String accessToken;
    private String refreshToken;
    private int expiresIn;
}
