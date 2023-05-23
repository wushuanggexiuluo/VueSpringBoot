package com.scm.myblog.entity.DTO;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("从前台接受的链接对象")
public class LinkDto {
    @ApiModelProperty("链接ID")
    private Long linkId;

    @ApiModelProperty("友链地址")
    @URL(message = "友链地址有误！")
    private String linkUrl;

    @ApiModelProperty("姓名")
    @NotBlank(message = "您的大名不能为空")
    private String linkName;

    @ApiModelProperty("头像")
    private String linkImage;

    @ApiModelProperty("描述")
    private String linkDescription;

    @ApiModelProperty("所属人名称")
    private String linkOwnerNickname;

    @ApiModelProperty("联系方式")
    private String linkOwnerContact;

    @ApiModelProperty("状态")
    private Integer linkStatus;
}
