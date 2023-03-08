package com.scm.myblog.entity.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {
    @ApiModelProperty("文章标题")
    private String articleTitle;
    @ApiModelProperty("评论ID")
    private Long commentId;
    @ApiModelProperty("评论人名称")
    private String commentAuthorName;
    @ApiModelProperty("评论人邮箱")
    private String commentAuthorEmail;
    @ApiModelProperty("评论人头像")
    private String commentAuthorAvatar;
    @ApiModelProperty("评论内容")
    private String commentContent;
    @ApiModelProperty("评论IP")
    private String commentIp;
    @ApiModelProperty("评论时间")
    private Timestamp commentCreateTime;
    @ApiModelProperty("评论角色")
    private long commentRole;
}
