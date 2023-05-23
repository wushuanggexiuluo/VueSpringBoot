package com.scm.myblog.entity.DOMAIN;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
  @TableId(type = IdType.AUTO)
  @ApiModelProperty("分类ID")
  private Long categoryId;
  @ApiModelProperty("分类名称")
  @TableField()
  private String categoryName;
  @ApiModelProperty("分类描述")
  private String categoryDescription;
  @ApiModelProperty("分类图标")
  private String categoryIcon;
}
