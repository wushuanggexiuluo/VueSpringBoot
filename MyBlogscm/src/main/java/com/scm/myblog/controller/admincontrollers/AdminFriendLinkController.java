package com.scm.myblog.controller.admincontrollers;

import com.scm.myblog.entity.DTO.LinkDto;
import com.scm.myblog.entity.DTO.LinkSearchDto;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.impl.FriendLinkServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Api(tags = "管理友链控制器")
@RestController
@RequestMapping("/Admin")
public class AdminFriendLinkController {
    @Autowired
    public FriendLinkServiceImpl links;

    @GetMapping("/get")
    public String get(){
        return "可以访问";
    }

    @ApiOperation("模糊查询加分页获取友链数据")
    @PostMapping("/getLinkPage")
    public Result getLinkPage(@RequestBody @Validated  PageDto<LinkDto, LinkSearchDto> pto){
        return links.getLinkPage(pto);
    }
    @ApiOperation("移除链接")
    @PostMapping("/removeLink")
    public Result removeLink(@RequestBody Integer[] Ids){
        return links.removeLink(Ids);
    }
    @ApiOperation("更新链接")
    @PostMapping("/updateLink")
    public Result updateLink(@RequestBody @Validated @ApiParam("友链dto对象") LinkDto dto){
        return links.updateLink(dto);
    }
}
