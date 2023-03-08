package com.scm.myblog.controller;


import com.scm.myblog.entity.Link;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.impl.FriendLinkServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(tags = "友链控制器")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Blog")
public class FriendLinkController {
    @Autowired
    public FriendLinkServiceImpl friendLinkService;
    @ApiOperation("获取所有链接")
    @GetMapping("/getAllLink")
    public Result getAllLink() {
        return friendLinkService.getAllLinks();
    }
    @ApiOperation("新增友链")
    @PostMapping("/addLink")
    public Result addLink(@RequestBody @Valid Link link, HttpServletRequest res, BindingResult r) {
        return friendLinkService.addLink(link,res);
    }
}
