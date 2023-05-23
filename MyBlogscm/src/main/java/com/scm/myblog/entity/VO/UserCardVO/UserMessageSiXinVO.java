package com.scm.myblog.entity.VO.UserCardVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMessageSiXinVO {
    private Integer id;
    private String userName;
    private String userAvatar;
    private String messageContent;
    private Date createTime;
}
