package com.yfh.bsecurity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class UserDO {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    /**
     * 登录账号
     */
    @TableField("login_name")
    private String loginName;


    /**
     * 用户类型（99系统用户,1普通用户,2付费用户,3终身用户）
     */
    @TableField("user_type")
    private Integer userType;

    /**
     * 密码
     */
    @TableField("password")
    private String password;
    
}
