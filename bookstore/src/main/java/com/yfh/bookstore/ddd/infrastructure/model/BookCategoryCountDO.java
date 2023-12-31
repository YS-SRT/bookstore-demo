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
 * 图书类别计数表
 * </p>
 *
 * @author yfh
 * @since 2022-05-10 16:45:13
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("book_category_count")
public class BookCategoryCountDO extends Model<BookCategoryCountDO> {

    private static final long serialVersionUID = 1L;

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
     * 包含书籍的数量
     */
    @TableField("count")
    private Integer count;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
