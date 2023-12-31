package com.yfh.register.ddd.domain.model;

public enum UserDelFlag {
    // 删除标志（0代表存在 1代表删除）
    Existed("0"),Deleted("1");

    private String sign;
    UserDelFlag(String sign){
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
