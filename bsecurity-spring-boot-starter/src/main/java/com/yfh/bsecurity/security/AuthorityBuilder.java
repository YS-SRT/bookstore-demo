package com.yfh.bsecurity.security;

import java.util.List;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

@FunctionalInterface
public interface AuthorityBuilder {

    List<GrantedAuthority> build(int userType);


    static AuthorityBuilder defaultBuilder = userType->{
            
        /**
         * 用户类型（0普通用户,1付费用户,2终身用户,99系统用户）
         */
        List<GrantedAuthority> authList = null;
        switch (userType) {
                case 99:
                    authList = AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN,ROLE_ADMIN");
                    break;
                case 1:
                    authList = AuthorityUtils.commaSeparatedStringToAuthorityList("VIP,ROLE_VIP");
                    break;
                case 2:
                    authList = AuthorityUtils.commaSeparatedStringToAuthorityList("LTS,ROLE_LTS");
                    break;
                case  0:
                default:
                    authList = AuthorityUtils.commaSeparatedStringToAuthorityList("USER,ROLE_USER");
                    break;

        }

        return authList;

    };
    
}
