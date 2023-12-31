package com.yfh.register.ddd.adapter.vo;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserVO {
    private Integer id;
    private String remark;
    private String loginName;
    private String userName;

    /**
     * 用户类型（0系统用户,1普通用户,2付费用户,3终身用户）
     */
    private Integer userType;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private Integer sex;

    /**
     * 头像路径
     */
    private String avatar;


    /**
     * 帐号状态（0正常 1停用 2禁止）
     */
    private Integer status;

    /**
     * 最后登陆IP
     */
    private String loginIp;

    /**
     * 最后登陆时间
     */
    private LocalDateTime loginTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;
    
}
