package com.scm.myblog.entity.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Valid
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("从前台留言的对象")

public class MessageDto {
    @NotBlank(message = "名字不能为空")
    @ApiModelProperty("留言人名称")
    private String messageAuthorName;
    @NotBlank
    @ApiModelProperty("留言人邮箱")
    private String messageAuthorEmail;
    @NotBlank
    @ApiModelProperty("留言内容")
    private String messageContent;
}
