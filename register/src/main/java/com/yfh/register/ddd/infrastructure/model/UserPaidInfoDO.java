package com.yfh.register.ddd.infrastructure.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户付费信息表
 * </p>
 *
 * @author yfh
 * @since 2022-05-06 17:40:59
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_paid_info")
public class UserPaidInfoDO extends Model<UserPaidInfoDO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private Integer userId;

    /**
     * 状态（0存在 1删除）
     */
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
