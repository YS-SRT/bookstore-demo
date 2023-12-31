package com.yfh.register.ddd.domain.model;

public enum UserStatus {
    // 帐号状态（0正常 1停用 2禁止）

    Normal("0"), Paused("1"), Forbidden("2");

    private String sign;
    UserStatus(String sign){
        this.sign = sign;
    }

    public String getSign(){
        return sign;
    }
    
}
