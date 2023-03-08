package com.scm.myblog.service.impl;

import com.scm.myblog.entity.StatusMes;
import com.scm.myblog.mapper.MessageMapper;
import com.scm.myblog.entity.Code;
import com.scm.myblog.entity.DTO.MessageDto;
import com.scm.myblog.entity.DTO.MessageSearch;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.Message;
import com.scm.myblog.entity.Tips;
import com.scm.myblog.entity.VO.MessageVo;
import com.scm.myblog.entity.VO.PageData;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.MessageService;
import com.scm.myblog.utils.DbUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 消息服务impl
 *
 * @author Lancer
 * @date 2022/12/07
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper ms;

    /**
     * 获得所有消息
     *
     * @return {@link Result}
     */
    public Result getAllMessage() {
        List<Message> messages = ms.selectList(null);
            List<MessageVo> lm = new ArrayList<>();
            for (Message message : messages) {
                MessageVo m = new MessageVo();
                BeanUtils.copyProperties(message, m);
                lm.add(m);
            }
            return new Result(lm, StatusMes.GET_OK.getCode(), StatusMes.GET_OK.getMes());
    }

    /**
     * 得到所有信息页
     *
     * @param pto pd
     * @return {@link Result}
     */
    public Result getAllMessagePage(PageDto<Message, MessageSearch> pto) {
        MessageSearch search = pto.getSearch();
        if(pto.getCurrPage()==null){
            pto.setCurrPage(1);
        }
        if (pto.getPageSize() == null) {
            pto.setPageSize(10);
        }
        pto.setCurrPage(DbUtils.getCurePage(pto.getCurrPage(),pto.getPageSize()));
        List<Message> messagePage = ms.getAllMessagePage(
                search.getMessageAuthorName(),
                search.getMessageAuthorEmail(),
                search.getMessageContent(),
                pto.getCurrPage(),
                pto.getPageSize());
        Integer i=ms.getAllMessageCount(
                search.getMessageAuthorName(),
                search.getMessageAuthorEmail(),
                search.getMessageContent());
        return new Result(new PageData<Message>(messagePage,i),StatusMes.PAGE_OK.getCode(),StatusMes.PAGE_OK.getMes());
    }
    /**
     * 设置消息
     *
     * @param msg 消息
     * @param res res
     * @return {@link Result}
     */
    public Result setMessage(MessageDto msg, HttpServletRequest res) {
        Message m = new Message();
        BeanUtils.copyProperties(msg, m);
        m.setMessageIp(res.getRemoteAddr());
        int i = ms.insert(m);
        return new Result(null, i > 0 ? StatusMes.SAVE_OK.getCode() : StatusMes.SAVE_ERR.getCode(), i > 0 ? StatusMes.SAVE_OK.getMes() : StatusMes.SAVE_ERR.getMes());
    }

    /**
     * 删除消息
     *
     * @param ids id
     * @return {@link Result}
     */
    public Result removeMessage(Integer[] ids) {
        int i=0;
        if(ids.length>1)
        {
             i= ms.deleteBatchIds(Arrays.asList(ids));
        }
        else {
            i=ms.deleteById(ids[0]);
        }
        return new Result(null,i>0?StatusMes.DELETE_OK.getCode(): StatusMes.DELETE_ERR.getCode(),i>0?StatusMes.DELETE_OK.getMes():StatusMes.DELETE_ERR.getMes());
    }

    /**
     * 更新消息
     *
     * @param message 消息dto
     * @return {@link Result}
     */
    public Result updateMessage(Message message) {
       int i= ms.updateById(message);
        return new Result(null,i>0?StatusMes.UPDATE_OK.getCode(): StatusMes.UPDATE_ERR.getCode(),i>0?StatusMes.UPDATE_OK.getMes():StatusMes.UPDATE_ERR.getMes());

    }
}
