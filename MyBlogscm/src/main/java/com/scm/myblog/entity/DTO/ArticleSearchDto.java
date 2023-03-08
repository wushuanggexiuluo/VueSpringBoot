package com.scm.myblog.entity.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("从前台接受的文章查找对象")
public class ArticleSearchDto {
    @Length(max = 50,message = "搜索文章内容过长！")
    @ApiModelProperty("内容")
    private String content;

    @Length(max = 50,message = "搜索文章标题过长！")
    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("标签")
    private String tag;
}
