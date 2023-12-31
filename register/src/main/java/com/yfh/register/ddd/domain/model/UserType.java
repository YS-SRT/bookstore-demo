package com.yfh.register.ddd.domain.model;

public enum UserType {
   //用户类型（00系统用户,01普通用户,02付费用户,03终身用户)
   SystemUser("00"),NormalUser("01"),PaidUser("02"),VIPUser("03"); 

   private String sign;

   UserType(String sign){
      this.sign = sign;
   }

   public String getSign() {
      return sign;
   }


}
