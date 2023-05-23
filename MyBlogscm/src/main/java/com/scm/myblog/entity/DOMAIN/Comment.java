package com.scm.myblog.entity.DOMAIN;


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
public class Comment {
  @TableId(type = IdType.AUTO)
  @ApiModelProperty("评论ID")
  private Long commentId;
  @ApiModelProperty("文章ID")
  private Long commentArticleId;
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
  @TableField(fill = FieldFill.INSERT)
  @ApiModelProperty("评论创建时间")
  private Timestamp commentCreateTime;
  @ApiModelProperty("评论角色")
  private long commentRole;
  @ApiModelProperty("评论等级")
  private Integer commentLevel;
  @ApiModelProperty("更新时间")
  private Timestamp commentUpdateTime;
  @ApiModelProperty("状态")
  private Integer status;
  @ApiModelProperty("是否删除")
  private Integer isDelete;

}
