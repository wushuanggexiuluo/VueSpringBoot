package com.scm.myblog.entity.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("从前台接受的用户评论对象")
public class UserCommentDto {
    @NotBlank(message = "评论名不能为空")
    @ApiModelProperty("评论人名称")
    private String commentAuthorName;
    @ApiModelProperty("评论人邮箱")
    private String commentAuthorEmail;
    @ApiModelProperty("评论人头像")
    private String commentAuthorAvatar;
    @NotBlank(message = "内容不能为空")
    @ApiModelProperty("评论内容")
    private String commentContent;
    @ApiModelProperty("评论IP")
    private String commentIp;
    @ApiModelProperty("评论角色")
    private long commentRole;
    @ApiModelProperty("文章标题")
    private String articleTitle;
}
