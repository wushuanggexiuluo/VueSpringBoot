package com.scm.myblog.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.scm.myblog.myannotation.EnumString;
import com.scm.myblog.myenum.RegxEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Link
{
  @TableId(type = IdType.AUTO)
  @ApiModelProperty("链接ID")
  private Long linkId;

  @EnumString(value =Regx.getInternet)
  @ApiModelProperty(value = "网站地址",required = true)
  private String linkUrl;

  @ApiModelProperty(value = "姓名",required = true)
  @NotBlank
  private String linkName;

  @ApiModelProperty("头像")
  private String linkImage;

  @ApiModelProperty(value = "描述",required = true)
  @NotBlank
  private String linkDescription;

  @ApiModelProperty(value = "所属人名称",required = true)
  @NotBlank
  private String linkOwnerNickname;

  @EnumString(value =Regx.getEmail)
  @ApiModelProperty(value = "qq邮箱",required = true)
  private String linkOwnerContact;

  @TableField( fill = FieldFill.UPDATE)
  @ApiModelProperty("更新时间")
  private Timestamp linkUpdateTime;

  @TableField( fill = FieldFill.INSERT)
  @ApiModelProperty("创建时间")
  private Timestamp linkCreateTime;

  @ApiModelProperty("状态")
  private Integer linkStatus;
}