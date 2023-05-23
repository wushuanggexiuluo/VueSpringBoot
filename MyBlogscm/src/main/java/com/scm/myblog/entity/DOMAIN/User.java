package com.scm.myblog.entity.DOMAIN;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.scm.myblog.myannotation.EnumString;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @ApiModelProperty("用户密码")
    private String userPassword;
    @ApiModelProperty("用户名称")
    private String userName;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("用户ID")
    private Integer userId;
    @ApiModelProperty("用户角色")
    private Integer userRole=2;
}
