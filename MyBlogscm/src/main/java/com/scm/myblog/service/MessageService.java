package com.scm.myblog.service;

import com.scm.myblog.entity.DTO.MessageDto;
import com.scm.myblog.entity.DTO.MessageSearch;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.Message;
import com.scm.myblog.entity.VO.Result;

import javax.servlet.http.HttpServletRequest;

public interface MessageService {
    /**
     * 获得所有消息
     *
     * @return {@link Result}
     */
     Result getAllMessage();

    /**
     * 获取所有信息页
     *
     * @param pto 美国专利商标局
     * @return {@link Result}
     */
    Result getAllMessagePage(PageDto<Message, MessageSearch> pto);

    /**
     * 设置留言
     *
     * @param msg 味精
     * @param res res
     * @return {@link Result}
     */
    Result setMessage(MessageDto msg, HttpServletRequest res);

    /**
     * 删除留言
     *
     * @param ids id
     * @return {@link Result}
     */
    Result removeMessage(Integer[] ids);

    /**
     * 更新留言
     *
     * @param message 留言
     * @return {@link Result}
     */
    Result updateMessage(Message message);
}
