package com.scm.myblog.entity.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("从前台接受的评论对象")
public class CommentDto {
    @ApiModelProperty("文章标题")
    @NotBlank(message = "文章标题不能为空")
    private String articleTitle;

    @ApiModelProperty("评论ID")
    private Long commentId;

    @NotBlank(message = "评论名不能为空")
    @ApiModelProperty("评论人名称")
    private String commentAuthorName;

    @ApiModelProperty("评论人邮箱")
    @NotBlank(message = "你的联系邮箱不能为空")
    private String commentAuthorEmail;

    @ApiModelProperty("评论人头像")
    private String commentAuthorAvatar;

    @NotBlank(message = "内容不能为空")
    @ApiModelProperty("评论内容")
    private String commentContent;

    @ApiModelProperty("评论IP")
    private String commentIp;

    @ApiModelProperty("评论时间")
    private Timestamp commentCreateTime;

    @ApiModelProperty("评论角色")
    private long commentRole;
}
