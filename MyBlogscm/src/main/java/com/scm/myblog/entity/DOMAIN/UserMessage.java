package com.scm.myblog.entity.DOMAIN;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName user_message
 */
@TableName(value ="user_message")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMessage implements Serializable {
    private Integer id;

    private Integer senderUid;

    private Integer getUid;

    private Integer status;

    private String messageContent;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}