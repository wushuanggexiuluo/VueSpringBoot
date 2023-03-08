package com.scm.myblog.service.impl;

import com.scm.myblog.mapper.CategoryMapper;
import com.scm.myblog.serviceUtils.AdminBlogUtils;
import com.scm.myblog.serviceUtils.RedisServiceBox;
import com.scm.myblog.serviceUtils.UserBlogUtils;
import com.scm.myblog.mapper.ArticleCategoryRefMapper;
import com.scm.myblog.mapper.ArticleMapper;

import com.scm.myblog.entity.*;
import com.scm.myblog.entity.DTO.ArticleDto;
import com.scm.myblog.entity.DTO.ArticleSearchDto;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AdminServiceImpl implements AdminService {
    private static CategoryMapper categoryMapper;

    @Autowired
    private CategoryMapper categoryMapperA;
    @Autowired
    private ArticleMapper articleMapperA;


    //在进入此类中时，此方法会被优先调用
    @PostConstruct
    private void init() {
        categoryMapper = categoryMapperA;
    }
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private ArticleCategoryRefMapper categoryRefDao;

    /**
     * 得到文章
     *
     * @param pageData 页面数据
     * @return {@link Result}
     */
    public Result getArticles(PageDto<Article, ArticleSearchDto> pageData) {
        return articleService.getArticlePage(pageData,true);
    }

    /**
     * 添加文章
     *
     * @param articleDto 文章dto
     * @return {@link Result}
     */
    public Result addArticle(ArticleDto articleDto, HttpServletRequest rq) {
        Long isE= UserBlogUtils.getIdByTitle(articleDto.getArticleTitle());
        if (isE == null) {
            Object url1 = rq.getSession().getAttribute("url");
            Article article=new Article();
            //插入
            //属性copy
            BeanUtils.copyProperties(articleDto,article);
            article.setArticleThImg((String)url1);
            int i = articleMapper.insert(article);

            //过滤标签
            ArticleDto articleDto1 = AdminBlogUtils.filterTaggers(articleDto);
            //----插入标签表-----
            AdminBlogUtils.insertNewTag(articleDto1.getCategory());
            //获取文章id
            Long artId = UserBlogUtils.getIdByTitle(articleDto.getArticleTitle());
            //-----插入文章标签映射表-----
            for (Category c:articleDto1.getCategory()) {
                ArticleCategoryRef a = new ArticleCategoryRef();
                a.setArticleId(artId);
                a.setCategoryId(categoryMapper.getIdByTagName(c.getCategoryName()));
                categoryRefDao.insert(a);
            }
            RedisServiceBox.addDataToRedis(articleDto.getArticleTitle());
            return new Result(articleDto.getArticleTitle(), i>0?StatusMes.SAVE_OK.getCode():StatusMes.SAVE_ERR.getCode(), i>0?StatusMes.SAVE_OK.getMes():StatusMes.SAVE_ERR.getMes());
        }
        else {
            return new Result(null,StatusMes.SAVE_ERR.getCode(), "文章名不能重复");
        }
    }

    /**
     * 更新文章
     *
     * @param articleDto 文章dto
     * @return {@link Result}
     */
    public Result updateArticles(ArticleDto articleDto,HttpServletRequest rq) {
        Object url = rq.getSession().getAttribute("url");
        //过滤标签
        ArticleDto articleDto1 = AdminBlogUtils.filterTaggers(articleDto);
        //----插入标签表-----
        List<Long> longIds = AdminBlogUtils.insertNewTag(articleDto1.getCategory());
        //属性copy
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        if(url!=null) {
            article.setArticleThImg((String) url);
        }//通过老标题获取id
        Long artId = UserBlogUtils.getIdByTitle(articleDto.getOldarticleTitle());
        //----更新文章表-----
        AdminBlogUtils.updateArticle(article,artId);
        //-----更新文章标签映射表-----
        AdminBlogUtils.updateCategoryRef(longIds,artId,article.getCategory());
        return new Result(null, StatusMes.UPDATE_OK.getCode(), StatusMes.UPDATE_OK.getMes());
    }

    /**
     * 删除文章
     *
     * @param ids id
     * @return {@link Result}
     */
    public Result removeArticles(Long[] ids) {
        List<Long> idss =Arrays.stream(ids).collect(Collectors.toList());
        boolean isRemoveSuccess=false;
        //从数据库中查出所有需要删除的文章id
        List<Article> batchIds = articleMapper.selectBatchIds(idss);
        //看数据库中是否存在
        if (batchIds.size() >= ids.length) {
            isRemoveSuccess=true;
            articleMapper.deleteBatchIds(idss);
        }
        Result result = new Result();
        if (isRemoveSuccess){
            result.setData(ids);
            result.setCode(StatusMes.DELETE_OK.getCode());
            result.setMessage(StatusMes.DELETE_OK.getMes());
        }
        else {
            result.setData(null);
            result.setCode(StatusMes.DELETE_ERR.getCode());
            result.setMessage(StatusMes.DELETE_ERR.getMes());
        }
        return result;
    }
}
