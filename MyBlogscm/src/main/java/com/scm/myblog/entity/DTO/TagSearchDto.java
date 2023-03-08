package com.scm.myblog.entity.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("从前台接受的标签查询对象")
public class TagSearchDto {
    @ApiModelProperty("分类名称")
    private String categoryName;
    @ApiModelProperty("分类描述")
    private String categoryDescription;
}
