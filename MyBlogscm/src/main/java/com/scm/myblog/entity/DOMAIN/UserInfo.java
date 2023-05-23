package com.scm.myblog.entity.DOMAIN;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @TableName user_info
 */
@TableName(value ="user_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {
    private Integer userId;

    private Integer userAvatarImg;

    private Integer userText;

    private Integer userSex;

    private Integer userBlog;

    private static final long serialVersionUID = 1L;
}