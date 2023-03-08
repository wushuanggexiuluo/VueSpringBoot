package com.scm.myblog.entity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @TableField(exist = false,select = false)
    @ApiModelProperty("分类")
    private List<Category> category;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("文章ID")
    private Long articleId;
    @ApiModelProperty("文章用户ID")
    private Long articleUserId;
    @ApiModelProperty("文章标题")
    private String articleTitle;
    @ApiModelProperty("文章内容")
    private String articleContent;
    @ApiModelProperty("文章访问量")
    private Long articleViewCount;
    @ApiModelProperty("文章评论数")
    private Long articleCommentCount;
    @ApiModelProperty("文章点赞数")
    private Long articleDianzanCount;
    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty("文章更新时间")
    private Timestamp articleUpdateTime;
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("文章创建时间")
    private Timestamp articleCreateTime;
    @ApiModelProperty("文章摘要")
    private String articleSummary;
    @ApiModelProperty("文章缩略图")
    private String articleThImg;

}
