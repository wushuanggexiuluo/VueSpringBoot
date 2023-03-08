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
public class LinkVo {

    @ApiModelProperty("URL")
    private String linkUrl;
    @ApiModelProperty("姓名")
    private String linkName;
    @ApiModelProperty("头像")
    private String linkImage;
    @ApiModelProperty("描述")
    private String linkDescription;
    @ApiModelProperty("所属人名称")
    private String linkOwnerNickname;
    @ApiModelProperty("更新时间")
    private Timestamp linkUpdateTime;
}
