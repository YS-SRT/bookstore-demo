package com.yfh.bookstore.ddd.infrastructure.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
 * 图书标签表
 * </p>
 *
 * @author yfh
 * @since 2022-05-10 16:45:13
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("book_tag")
public class BookTagDO extends Model<BookTagDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 标签id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类别id
     */
    @TableField("category_id")
    private Integer categoryId;

    /**
     * 标签名称
     */
    @TableField("name")
    private String name;

    /**
     * flat（0代表存在 1代表删除）
     */
    @TableField("del_flag")
    @TableLogic
    private String delFlag;

    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;

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


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
