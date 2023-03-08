package com.scm.myblog.entity.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageVo {
    @ApiModelProperty("留言人名称")
    private String messageAuthorName;
    @ApiModelProperty("留言人邮箱")
    private String messageAuthorEmail;
    @ApiModelProperty("留言内容")
    private String messageContent;
    @ApiModelProperty("留言时间")
    private Timestamp messageCreateTime;
}
