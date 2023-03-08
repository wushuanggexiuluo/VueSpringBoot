package com.scm.myblog.entity.VO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminCommentVo {
    @ApiModelProperty("文章标题")
    private String articleTitle;
    @ApiModelProperty("评论ID")
    private Long commentId;
    @ApiModelProperty("文章ID")
    private long commentArticleId;
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
