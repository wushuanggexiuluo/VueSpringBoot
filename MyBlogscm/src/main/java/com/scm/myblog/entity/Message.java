package com.scm.myblog.entity;


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
public class Message {
  /**
   * 消息id
   */
  @TableId(type = IdType.AUTO)
  @ApiModelProperty("留言ID")
  private Long messageId;
  /**
   * 消息作者名称
   */
  @ApiModelProperty("留言人名称")
  private String messageAuthorName;
  @ApiModelProperty("留言人邮箱")
  private String messageAuthorEmail;
  @ApiModelProperty("留言人头像")
  private String messageAuthorAvatar;
  @ApiModelProperty("留言内容")
  private String messageContent;
  @ApiModelProperty("留言IP")
  private String messageIp;
  @TableField(fill = FieldFill.INSERT)
  @ApiModelProperty("留言时间")
  private Timestamp messageCreateTime;
  @ApiModelProperty("留言角色")
  private long messageRole;
}
