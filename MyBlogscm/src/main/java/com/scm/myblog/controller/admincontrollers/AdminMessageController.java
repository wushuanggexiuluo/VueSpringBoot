package com.scm.myblog.controller.admincontrollers;

import com.scm.myblog.entity.DOMAIN.Message;
import com.scm.myblog.entity.DTO.MessageSearch;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.impl.MessageServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 消息管理控制器
 *
 * @author Lancer
 * @date 2022/12/07
 */
@Api(tags = "管理留言控制器")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Admin")
public class AdminMessageController {
    @Autowired
    public MessageServiceImpl messageService;

    /**
     * 获得所有消息
     *
     * @return {@link Result}
     */
    @ApiOperation("模糊查询加分页获取留言数据")
    @PostMapping("/getMessagePage")
    public Result getAllMessage(@RequestBody  PageDto<Message, MessageSearch> pageData) {
        return messageService.getAllMessagePage(pageData);
    }

    /**
     * 删除消息
     *
     * @param Ids id
     * @return {@link Result}
     */
    @ApiOperation("移除留言")
    @PostMapping("/removeMessage")
    public Result removeMessage(@RequestBody Integer[] Ids) {
        return messageService.removeMessage(Ids);
    }
    @ApiOperation("更新留言")
    @PostMapping("/updateMessage")
    public Result updateMessage(@RequestBody Message message) {
        return messageService.updateMessage(message);
    }
}
