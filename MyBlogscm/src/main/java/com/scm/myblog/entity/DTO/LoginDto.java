package com.scm.myblog.entity.DTO;

import com.scm.myblog.myannotation.EnumString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("从前台接受的登陆对象")
public class LoginDto {

    @NotBlank(message = "用户名不能为空！")
    @Length(max=20,message = "用户名过长！")
    @ApiModelProperty("用户名")
    private String userName;

    @NotBlank(message = "密码不能为空！")
    @Length(min = 6,max = 32,message = "密码不和要求！")
    @ApiModelProperty("用户密码")
    private String userPassword;

}
