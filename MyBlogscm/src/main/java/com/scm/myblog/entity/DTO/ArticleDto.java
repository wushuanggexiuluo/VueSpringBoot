package com.scm.myblog.entity.DTO;

import com.scm.myblog.entity.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
@Valid
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description="从前台接受的文章对象")
public class ArticleDto {
    @ApiModelProperty("标签列表")
    private List<Category> category;

//    @ApiModelProperty(value = "老的文章标题",dataType = "String")
//    @NotBlank(message = "老的文章标题不能为空")
    private String oldarticleTitle;

    @ApiModelProperty("新的文章标题")
    @NotBlank(message = "文章标题不能为空")
    private String articleTitle;
    @NotBlank(message = "文章内容不能为空")
    private String articleContent;
    @NotBlank(message = "摘要不能为空")
    private String articleSummary;
    @ApiModelProperty("文章缩略图")
    private String articleThImg;
}
