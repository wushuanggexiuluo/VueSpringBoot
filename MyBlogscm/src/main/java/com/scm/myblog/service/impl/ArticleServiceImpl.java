package com.scm.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scm.myblog.entity.BO.DPLDataBo;
import com.scm.myblog.serviceUtils.RedisServiceBox;
import com.scm.myblog.serviceUtils.UserBlogUtils;
import com.scm.myblog.mapper.*;
import com.scm.myblog.entity.*;
import com.scm.myblog.entity.DTO.ArticleSearchDto;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.VO.PageData;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.ArticleService;
import com.scm.myblog.utils.DbUtils;
import com.scm.myblog.utils.WebUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static com.scm.myblog.serviceUtils.UserBlogUtils.getManyArticleTaggersByIdOrTitle;
import static com.scm.myblog.serviceUtils.UserBlogUtils.getSingleArticleTaggersByIdOrTitle;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    private static String Glotitle;

    public Result getAllBoutique(){
        List<Long> newIds = new ArrayList<>();
        UserBlogUtils.getHotArticleIds(newIds);
        List<Article> articles = articleMapper.selectBatchIds(newIds);

        //获取标签
        UserBlogUtils.getManyArticleTaggersByIdOrTitle(articles);
        //根据ids获取redis中的数据

        for (Article art : articles){
            Long id = art.getArticleId();
            if (newIds.contains(id)){
            BeanUtils.copyProperties(RedisServiceBox.getSingleDPLDataFromRedisById(id),art);
            };
        }
        for (Article article : articles) {
            article.setArticleId(null);
            article.setArticleUserId(null);
            article.setArticleContent(null);
        }
        return new Result(articles,StatusMes.GET_OK.getCode(),StatusMes.GET_OK.getMes());
    }

    /**
     * 得到分页文章数据
     *
     * @param pageData      页面数据
     * @param isFindContent 是否查找文章的具体内容
     * @return {@link Result}
     */
    public Result getArticlePage(PageDto<Article, ArticleSearchDto> pageData, Boolean isFindContent) {
        //模糊查询
        LambdaQueryWrapper<Article> getLikeResult = new LambdaQueryWrapper<>();
        if (isFindContent) {
            getLikeResult.select(
                    Article::getArticleId,
                    Article::getArticleSummary,
                    Article::getArticleCreateTime,
                    Article::getArticleThImg,
                    Article::getArticleTitle,
                    Article::getArticleContent);
        } else {
            getLikeResult.select(
                    Article::getArticleId,
                    Article::getArticleSummary,
                    Article::getArticleCreateTime,
                    Article::getArticleThImg,
                    Article::getArticleTitle);
        }
        getLikeResult = getLikeResult.like(Article::getArticleContent, pageData.getSearch().getContent())
                .like(Article::getArticleTitle, pageData.getSearch().getTitle());
        //对模糊的结果进行分页
        IPage<Article> iPage = pageData.pageBuild();
        IPage<Article> pageRes = articleMapper.selectPage(iPage, getLikeResult);
        //获取第一次查询的结果
        List<Article> records = pageRes.getRecords();
        //设置各个文章中的浏览量数据
        List<DPLDataBo> listFromRedis = RedisServiceBox.getAllDPLDataFromRedis();
        List<DPLDataBo> newList = listFromRedis.stream().sorted(Comparator.comparing(DPLDataBo::getArticleId)).collect(Collectors.toList());
        Integer pageSize=pageData.getPageSize();
        Integer currentPage=pageData.getCurrPage();
        int temp = 0;
        //查询的res小于请求的结果时
        if (pageSize>records.size()){
            temp= (int) pageRes.getTotal();
        }
        else {
            temp=pageData.getCurrPage()*pageSize;
        }
        //边界条件
        int start=DbUtils.getCurePage(currentPage,pageSize);
        if (start<=temp){
            for (int i =start; i < temp; i++) {
                BeanUtils.copyProperties(newList.get(i), records.get(i-start));
            }
        }

        //传入list获取所有标签 封装结果
        PageData<Article> resPageData = new PageData<Article>(getManyArticleTaggersByIdOrTitle(records), (int) pageRes.getTotal());
        return new Result(resPageData, StatusMes.PAGE_OK.getCode(), StatusMes.PAGE_OK.getMes());
    }


    /**
     * 获取单个文章的数据
     *
     * @param title 标题
     * @return {@link Result}
     */
    public Result getArticleData(String title) {
        Glotitle=title;
        //获取文章内容
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Article::getArticleId,
                Article::getArticleContent,
                Article::getArticleCreateTime,
                Article::getArticleUpdateTime,
                Article::getArticleTitle,
                Article::getArticleViewCount);
        wrapper.eq(Article::getArticleTitle, title);
        Article article = articleMapper.selectOne(wrapper);

        //获取文章标签
        getSingleArticleTaggersByIdOrTitle(article);
        //填充点赞数据
        DPLDataBo dataBo = RedisServiceBox.getSingleDPLDataFromRedisByTitle(title);
        article.setArticleViewCount(dataBo.getArticleViewCount());
        article.setArticleCommentCount(dataBo.getArticleCommentCount());
        article.setArticleDianzanCount(dataBo.getArticleDianzanCount());

        return new Result(article, StatusMes.GET_OK.getCode(), StatusMes.GET_OK.getMes());
    }

    /**
     * 返回时间线文章，降序
     *
     * @return {@link Result}
     */
    public Result getArticlesByTimeLine() {
        LambdaQueryWrapper<Article> a=new LambdaQueryWrapper<>();
        a.select(Article::getArticleTitle,Article::getArticleCreateTime,Article::getArticleSummary).orderByDesc(Article::getArticleCreateTime);
        List<Article> articles = articleMapper.selectList(a);
        return new Result(articles,StatusMes.GET_OK.getCode(),StatusMes.GET_OK.getMes());
    }

    //设置文章点赞量
    public Result setDianZan(Integer d, HttpServletRequest request) {
//        String title = WebUtils.getValBySession(request, "title");
        RedisServiceBox.setDianZanCount(Glotitle,d);
        return new Result(null,StatusMes.UPDATE_OK.getCode(),StatusMes.UPDATE_OK.getMes());
    }

    public Result getArticlesBySearch(String search) {
        //模糊查询
        LambdaQueryWrapper<Article> getLikeResult = new LambdaQueryWrapper<>();
            getLikeResult.select(
                    Article::getArticleId,
                    Article::getArticleSummary,
                    Article::getArticleCreateTime,
                    Article::getArticleThImg,
                    Article::getArticleTitle);
        getLikeResult = getLikeResult.like(Article::getArticleContent, search).or()
                .like(Article::getArticleTitle, search);
        List<Article> records = articleMapper.selectList(getLikeResult);
        //设置各个文章中的浏览量数据
        for (Article d:records) {
            DPLDataBo data = RedisServiceBox.getSingleDPLDataFromRedisById(d.getArticleId());
            BeanUtils.copyProperties(data,d);
            d.setArticleId(null);
        }
        return new Result(records, StatusMes.GET_OK.getCode(), StatusMes.GET_OK.getMes());

    }
}
