package com.scm.myblog.entity.DOMAIN;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @TableName kind_tree_menu
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class KindTreeMenu implements Serializable {
    @TableField(exist = false)
    List<KindTreeMenu> childData;

    private Integer id;

    private String menuName;

    private Integer parentId;
    private Integer orderBy=1;


    private String menuPath;

    private Integer status;

    private Integer isDelete;


    private static final long serialVersionUID = 1L;
}