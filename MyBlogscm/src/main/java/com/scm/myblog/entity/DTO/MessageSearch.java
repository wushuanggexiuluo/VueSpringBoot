package com.scm.myblog.entity.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("从前台接受消息查询的对象")

public class MessageSearch {
    @ApiModelProperty("留言人名称")
    private String messageAuthorName;
    @ApiModelProperty("留言人邮箱")
    private String messageAuthorEmail;
    @ApiModelProperty("留言内容")
    private String messageContent;
}
