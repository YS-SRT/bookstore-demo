package com.yfh.register.ddd.domain.model;

public enum UserSex {
    // 0男 1女 2未知
    Male("0"),Female("1"),Unknown("2");
    private String sign;
    UserSex(String sign){
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

}
