package com.scm.myblog.controller;

import com.scm.myblog.entity.Article;
import com.scm.myblog.entity.DTO.ArticleSearchDto;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.impl.ArticleServiceImpl;
import com.scm.myblog.utils.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "文章控制器")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Blog")
public class ArticleController {
    @Autowired
    public ArticleServiceImpl articleService;
    @ApiOperation("设置点赞")
    @GetMapping("/setDianZan/{Count}")
    public Result setDianZan(@PathVariable Integer Count,HttpServletRequest request) {
        return articleService.setDianZan(Count,request);
    }
    @ApiOperation("模糊查询加分页获取文章数据")
    @PostMapping("/getPageData")
    public Result getArticleByPageAndSearch(@RequestBody PageDto<Article, ArticleSearchDto> pageData) {
        return articleService.getArticlePage(pageData,false);
    }
    @ApiOperation("获取详细文章内容")
    @GetMapping("/getArticles/{title}")
    public Result getArticleDetail(@PathVariable("title") String title, HttpServletRequest request) {
        //将文章标题放在session中备用
        WebUtils.setValBySession(request,"title",title);
        return articleService.getArticleData(title);
    }
    /**
     * 获取文章归档
     *
     */
    @ApiOperation("根据时间线获取文章")
    @GetMapping ("/getArticlesByTimeLine")
    public Result getArticlesByTimeLine(){
        return articleService.getArticlesByTimeLine();
    }

    /**
     * 获取文章归档
     *
     */
    @ApiOperation("获取文章通过指定搜索")
    @GetMapping ("/getArticlesSearch/{search}")
    public Result getArticlesSearch(@PathVariable("search")String search){

        return articleService.getArticlesBySearch(search);
    }


}
