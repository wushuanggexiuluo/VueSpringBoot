package com.scm.myblog.entity.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("从前台接受的链接查询对象")
public class LinkSearchDto {
    @ApiModelProperty("所属人名称")
    private String linkOwnerNickname;
    @ApiModelProperty("姓名")
    private String linkName;
}
