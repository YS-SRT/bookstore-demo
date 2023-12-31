package com.yfh.bsecurity.security;

import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class BSUserDetails extends User{

    BSUserDetails(String username, String password, Integer userId, Integer userType){
       super(username, password, AuthorityBuilder.defaultBuilder.build(userType));
       this.userId = userId;
       this.userType = userType;
    }

    private Integer userId;
    private Integer userType;
    

}
