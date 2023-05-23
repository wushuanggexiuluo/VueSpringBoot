package com.scm.myblog.controller.blogcontrollers;

import com.scm.myblog.entity.DTO.MessageDto;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.impl.MessageServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "留言控制器")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Blog")
public class MessageController {
    @Autowired
    public MessageServiceImpl messageService;
    @ApiOperation("获取所有留言")
    @GetMapping("/getAllMessage")
    public Result getAllMessage() {
        return messageService.getAllMessage();
    }
    @ApiOperation("设置留言")
    @PostMapping("/setMessage")
    public Result setMessage(@RequestBody @Validated @ApiParam("留言dto对象")MessageDto ms, HttpServletRequest res) {
        return messageService.setMessage(ms,res);
    }
}
