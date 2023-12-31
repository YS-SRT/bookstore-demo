package com.yfh.register.ddd.domain.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserE {
   private int id;
   private String loginName;
   private String userName;
   private String userType = UserType.NormalUser.getSign();
   private String sex = UserSex.Unknown.getSign();
   private String password;
   private String salt;
   @Pattern(regexp="^[1][3,4,5,7,8,9][0-9]{9}$", message="手机号码不合法")
   private String phoneNumber;
   @Email
   private String email;
   private String avatar;
   private String remark;
   private String status = UserStatus.Normal.getSign();
   private String delFlag = UserDelFlag.Existed.getSign();

   private String loginIp;
   private Date loginDate;
   private Date createTime;
   private String createBy;
   private Date updateTime;
   private String updateBy;
}
