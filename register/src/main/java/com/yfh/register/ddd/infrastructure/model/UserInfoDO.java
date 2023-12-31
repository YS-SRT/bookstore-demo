package com.yfh.register.ddd.infrastructure.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author yfh
 * @since 2022-05-06 17:40:58
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_info")
public class UserInfoDO extends Model<UserInfoDO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 登录账号
     */
    @TableField("login_name")
    private String loginName;

    /**
     * 用户昵称
     */
    @TableField("user_name")
    private String userName;

    /**
     * 用户类型（0系统用户,1普通用户,2付费用户,3终身用户）
     */
    @TableField("user_type")
    private Integer userType;

    /**
     * 用户邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机号码
     */
    @TableField("phone_number")
    private String phoneNumber;

    /**
     * 用户性别（0女 1男 2未知）
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 头像路径
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 盐加密
     */
    @TableField("salt")
    private String salt;

    /**
     * 帐号状态（0正常 1停用 2禁止）
     */
    @TableField("status")
    private Integer status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;

    /**
     * 最后登陆IP
     */
    @TableField("login_ip")
    private String loginIp;

    /**
     * 最后登陆时间
     */
    @TableField("login_time")
    private LocalDateTime loginTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 版本号(乐观锁)
     */
    @TableField("version")
    @Version
    private Integer version;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
