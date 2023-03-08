package com.scm.myblog.controller;

import com.scm.myblog.entity.Category;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.DTO.TagSearchDto;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.impl.TagServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "标签控制器")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Blog")
public class TagController {
    @Autowired
    TagServiceImpl t;
    @GetMapping("/getAllHotTag")
    public Result getAllHotTag(){
        return t.getAllHotTag();
    }

    @ApiOperation("模糊查询加分页获取标签数据")
    @PostMapping("/getAllTag")
    public Result getAllTag(@RequestBody @ApiParam("标签dto对象")PageDto<Category, TagSearchDto> p){
        return t.getAllTag(p);
    }
    @ApiOperation("根据标签获取文章")
    @GetMapping("/getTagArticle/{tag}")
    public Result getTagArticle(@PathVariable("tag")String tag){
        return t.getArticleByTag(tag);
    }
}
