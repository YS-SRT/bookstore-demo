package com.yfh.bookstore.ddd.infrastructure.model;

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
 * 图书信息表
 * </p>
 *
 * @author yfh
 * @since 2022-05-10 16:45:13
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("book_info")
public class BookInfoDO extends Model<BookInfoDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类别id
     */
    @TableField("category_id")
    private Integer categoryId;

    /**
     * 类别名称
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 书名
     */
    @TableField("name")
    private String name;

    /**
     * 作者
     */
    @TableField("author")
    private String author;

    /**
     * ISBN编号
     */
    @TableField("isbn")
    private String isbn;

    /**
     * 标签，格式：#tag1;#tag2
     */
    @TableField("tags")
    private String tags;

    /**
     * 出版时间
     */
    @TableField("publish_time")
    private LocalDateTime publishTime;

    /**
     * 简介
     */
    @TableField("brief")
    private String brief;

    /**
     * 逻辑删除（0代表存在 1代表删除）
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

    /**
     * 修改乐观锁
     */
    @TableField("version")
    @Version
    private Integer version;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
