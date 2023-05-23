package com.scm.myblog.controller.admincontrollers;

import com.scm.myblog.common.ValidatedLancer.ValidatedGroup;
import com.scm.myblog.entity.DOMAIN.Article;
import com.scm.myblog.entity.DTO.ArticleDto;
import com.scm.myblog.entity.DTO.ArticleSearchDto;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.impl.AdminServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    private AdminServiceImpl adminService;


    @GetMapping ("/setArticleSort/{sortId}")
    @ApiOperation("设置前台的主页文章排序")
    public Result setArticleSort(@PathVariable("sortId") Integer[] sortId){
        return adminService.setArticleSort(sortId);
    }


    @GetMapping ("/setArticleSortOnJH/{sortId}")
    @ApiOperation("设置前台的精华文章排序")
    public Result setArticleSortOnJH(@PathVariable("sortId") Integer sortId){
        return adminService.setArticleSortOnJH(sortId);
    }

    @GetMapping("/setArticleSortOnGD/{sortId}")
    @ApiOperation("设置前台的文章归档的排序")
    public Result setArticleSortOnGD(@PathVariable("sortId") Integer sortId){
        return adminService.setArticleSortOnGD(sortId);
    }
    @PostMapping ("/getArticles")
    @ApiOperation("模糊查询加分页获取文章数据")
    public Result getArticles(@RequestBody
                                  @ApiParam(type = "PageDto",required=true,value = "传入PageDto对象，里面包含当前页，页面尺寸，和搜索的数据")
                                  @Validated PageDto<Article, ArticleSearchDto> pageData){
        return adminService.getArticles(pageData);
    }

    /**
     * 添加文章
     *
     * @param articleDto 文章dto
     * @param request    请求
     * @return {@link Result}
     */
    @ApiOperation("新增文章")
    @PostMapping ("/addArticles")
    public Result addArticles(@RequestBody
                                  @Validated(ValidatedGroup.Insert.class)
                                  @ApiParam(type = "ArticleDto",
                                          required=true,
                                          value = "文章dto对象")
                                  ArticleDto articleDto,
                              HttpServletRequest request){
        return adminService.addArticle(articleDto,request);
    }

    /**
     * 更新文章
     *
     * @param articleDto 文章dto
     * @return {@link Result}
     */
    @ApiOperation("更新文章")
    @PostMapping ("/updateArticles")
    public Result updateArticles(@RequestBody
                                     @Validated(ValidatedGroup.Update.class)
                                     @ApiParam(type = "ArticleDto",
                                             required=true,
                                             value = "文章dto对象")
                                     ArticleDto articleDto,
                                 HttpServletRequest request){
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
