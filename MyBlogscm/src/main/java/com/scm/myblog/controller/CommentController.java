package com.scm.myblog.controller;

import com.scm.myblog.entity.Code;
import com.scm.myblog.entity.DTO.UserCommentDto;
import com.scm.myblog.entity.Tips;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.exception.DefinitionException;
import com.scm.myblog.service.impl.CommentServiceImpl;
import com.scm.myblog.utils.CharsetFilterUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "评论控制器")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Blog")
public class CommentController {

    @Autowired
    public CommentServiceImpl commentService;
    @ApiOperation("模糊查询加分页获取评论数据")
    @PostMapping("/setComment")
    public Result setComment(@RequestBody @Valid @ApiParam("评论dto对象")UserCommentDto comment, BindingResult r) {
        comment.setArticleTitle(CharsetFilterUtils.tranCharset(comment.getArticleTitle()));
        if(r.hasErrors()){
            throw new DefinitionException(Code.SAVE_ERR, Tips.SAVE_ERR);
        }
        return commentService.setComment(comment);
    }
    @ApiOperation("获取文章评论")
    @GetMapping("/getComment/{title}")
    public Result getArticleComment(@PathVariable("title") String title) {
        return commentService.getArticleComment(title);
    }

}
