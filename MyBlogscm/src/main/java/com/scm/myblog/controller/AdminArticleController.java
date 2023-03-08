package com.scm.myblog.controller;

import com.scm.myblog.entity.Article;
import com.scm.myblog.entity.Code;
import com.scm.myblog.entity.DTO.ArticleDto;
import com.scm.myblog.entity.DTO.ArticleSearchDto;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.Tips;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.exception.DefinitionException;
import com.scm.myblog.service.impl.AdminServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 管理后台控制器
 *
 * @author Lancer
 * @date 2022/12/04
 */
@Api(tags = "管理后台文章控制器")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Admin")
public class AdminArticleController
{
    @Autowired
    public AdminServiceImpl adminService;

    @PostMapping ("/getArticles")
    @ApiOperation("模糊查询加分页获取文章数据")
    public Result getArticles(@RequestBody @ApiParam(type = "PageDto",required=true,value = "传入PageDto对象，里面包含当前页，页面尺寸，和搜索的数据") PageDto<Article, ArticleSearchDto> pageData){
        return adminService.getArticles(pageData);
    }

    /**
     * 添加文章
     *
     * @param articleDto 文章dto
     * @param b          b
     * @param request    请求
     * @return {@link Result}
     */
    @ApiOperation("新增文章")
    @PostMapping ("/addArticles")
    public Result addArticles(@RequestBody
                                  @Valid
                                  @ApiParam(type = "ArticleDto",
                                          required=true,
                                          value = "文章dto对象")
                                  ArticleDto articleDto,
                              BindingResult b,
                              HttpServletRequest request){
        if(b.hasErrors()){
            throw new DefinitionException(Code.SAVE_ERR, Tips.SAVE_ERR);
        }
        return adminService.addArticle(articleDto,request);
    }

    /**
     * 更新文章
     *
     * @param articleDto 文章dto
     * @param b          b
     * @return {@link Result}
     */
    @ApiOperation("更新文章")
    @PostMapping ("/updateArticles")
    public Result updateArticles(@RequestBody
                                     @Valid
                                     @ApiParam(type = "ArticleDto",
                                             required=true,
                                             value = "文章dto对象")
                                     ArticleDto articleDto,
                                 BindingResult b,
                                 HttpServletRequest request){
        if(b.hasErrors()){
            throw new DefinitionException(Code.UPDATE_ERR, Tips.UPDATE_ERR);
        }
        return adminService.updateArticles(articleDto,request);
    }

    /**
     * 删除文章
     *
     * @param arrIDs ids
     * @return {@link Result}
     */
    @ApiOperation("删除文章")
    @PostMapping ("/removeArticles")
    public Result removeArticles(@RequestBody  @ApiParam("文章id数组的形式")  Long[] arrIDs){
        return adminService.removeArticles(arrIDs);
    }


}
