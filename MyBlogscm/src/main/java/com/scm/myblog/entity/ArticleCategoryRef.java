package com.scm.myblog.entity;


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
public class ArticleCategoryRef {

  @TableField(exist = false)
  @ApiModelProperty("分类")
  private Category category;
  @ApiModelProperty("文章ID")
  private Long articleId;
  @ApiModelProperty("分类ID")
  private Long categoryId;
  public ArticleCategoryRef(Long articleId,Long categoryId) {
      this.articleId = articleId;
      this.categoryId = categoryId;
  }

}
