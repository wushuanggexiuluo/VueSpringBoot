package com.scm.myblog.controller;

import com.scm.myblog.entity.DTO.CommentDto;
import com.scm.myblog.entity.DTO.CommentSearchDto;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.impl.CommentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@Api(tags = "管理评论控制器")
@RestController
@RequestMapping("/Admin")
public class AdminCommentController {
    @Autowired
    public CommentServiceImpl commentService;
    @ApiOperation("模糊查询加分页获取评论数据")
    @PostMapping("/getCommentPage")
    public Result getCommentPage(@RequestBody @Valid @ApiParam("评论dto对象") PageDto<CommentDto, CommentSearchDto> pto){
        return commentService.getCommentPage(pto);
    }
    @ApiOperation("删除评论")
    @PostMapping("/removeComment")
    public Result removeComment(@RequestBody Long[] Ids){
        return commentService.removeComment(Ids);
    }
    @ApiOperation("更新评论")
    @PostMapping("/updateComment")
    public Result updateComment(@RequestBody @Valid CommentDto dto){
        return commentService.updateComment(dto);
    }
}
