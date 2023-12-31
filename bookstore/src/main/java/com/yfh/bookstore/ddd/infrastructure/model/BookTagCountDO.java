package com.yfh.bookstore.ddd.infrastructure.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 图书标签计数表
 * </p>
 *
 * @author yfh
 * @since 2022-05-10 16:45:13
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("book_tag_count")
public class BookTagCountDO extends Model<BookTagCountDO> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标签id
     */
    @TableField("tag_id")
    private Integer tagId;

    /**
     * 标签名称
     */
    @TableField("tag_name")
    private String tagName;

    /**
     * 标签包含的书籍数量
     */
    @TableField("count")
    private Integer count;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
