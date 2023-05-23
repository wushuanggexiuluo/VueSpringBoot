package com.scm.myblog.entity.DOMAIN;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Name {
  @TableId(type = IdType.AUTO)
  @ApiModelProperty("名称ID")
  private Long nameId;
  @ApiModelProperty("称号")
  private String nameNickname;
  @ApiModelProperty("友人ID")
  private long nameLinkId;
}
