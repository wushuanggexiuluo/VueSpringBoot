package com.scm.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scm.myblog.entity.BO.DPLDataBo;
import com.scm.myblog.entity.CORE.StatusMes;
import com.scm.myblog.entity.DOMAIN.Article;
import com.scm.myblog.entity.DOMAIN.UserArticleController;
import com.scm.myblog.entity.DOMAIN.UserArticleData;
import com.scm.myblog.entity.DTO.SouCangDto;
import com.scm.myblog.manager.CacheManage.CaffeineManage;
import com.scm.myblog.manager.RedisServiceBox;
import com.scm.myblog.manager.UserBlogUtils;
import com.scm.myblog.mapper.*;
import com.scm.myblog.entity.DTO.ArticleSearchDto;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.VO.PageData;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.ArticleService;
import com.scm.myblog.utils.DbUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static com.scm.myblog.manager.UserBlogUtils.getManyArticleTaggersByIdOrTitle;
import static com.scm.myblog.manager.UserBlogUtils.getSingleArticleTaggersByIdOrTitle;


@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserArticleDataMapper uadmp;

    @Autowired
    private UserArticleControllerMapper uaController;

    private static String Glotitle;

    @Cacheable(value = CaffeineManage.BoutiqueArticles)
    public Result getAllBoutique(){
        //读取数据库配置中的排序id
        Integer sortId = uaController.selectById(1).getJingHuaSortId();
        List<Long> newIds = new ArrayList<>();
        List<Article> articles = new ArrayList<>();
        if (sortId==1){
            UserBlogUtils.getHotArticleIdsByDianZan(newIds,1);
        }
        else {
            UserBlogUtils.getHotArticleIdsByDianZan(newIds,2);
        }
        for (Long newId : newIds) {
            articles.add(articleMapper.selectById(newId));
        }
        //获取标签
        getManyArticleTaggersByIdOrTitle(articles);
        //根据ids获取redis中的数据
        for (Article art : articles){
            Long id = art.getArticleId();
            if (newIds.contains(id)){
            BeanUtils.copyProperties(RedisServiceBox.getSingleDPLDataFromRedisById(id),art);
            }
        }
        for (Article article : articles) {
            article.setArticleId(null);
            article.setArticleUserId(null);
            article.setArticleContent(null);
        }
        return new Result(articles,StatusMes.GET_OK.getCode(), StatusMes.GET_OK.getMessage());
    }

    @CacheEvict(value = CaffeineManage.UserShouCangData)
    @Override
    public Result setShouCang(SouCangDto dto) {
        Integer uid=dto.getUid();
        Integer articleId=dto.getArticleId();
        LambdaQueryWrapper<UserArticleData> u=new LambdaQueryWrapper<>();
        u=u.eq(UserArticleData::getArticleId,articleId).eq(UserArticleData::getUid,uid);
        // 构建实体
        UserArticleData udat = new UserArticleData();
        udat.setArticleId(articleId);
        udat.setUid(uid);
        udat.setIsShouCang(1);

        List<UserArticleData> data = uadmp.selectList(u);
        int i;
        if (data.size() > 0){
            i=uadmp.update(udat,u);
        }
        else {
            i=uadmp.insert(udat);
        }
        Result result = new Result();
        if (i>0){
            result.setCode("200");
            result.setMessage("收藏成功！");
        }
        else {
            result.setCode("201");
            result.setMessage("收藏失败！");
        }
        return result;
    }
    @Cacheable(value = CaffeineManage.UserShouCangData)
    @Override
    public Result getShouCangData(Integer uid) {
        LambdaQueryWrapper<UserArticleData> u=new LambdaQueryWrapper<>();
        u=u.eq(UserArticleData::getUid,uid).eq(UserArticleData::getIsShouCang,1).select(UserArticleData::getArticleId);
        List<UserArticleData> articleData = uadmp.selectList(u);
        List<Article> articleList=new ArrayList<>();
        if (articleData.size()>0){
            List<Integer> collect = articleData.stream().map(UserArticleData::getArticleId).collect(Collectors.toList());
            //查询文章
            List<Article> articles = articleMapper.selectBatchIds(collect);
            //设置点赞收藏数据
            //设置各个文章中的浏览量数据
            for (Article article: articles) {
                DPLDataBo dataBo = RedisServiceBox.getSingleDPLDataFromRedisById(article.getArticleId());
                article.setArticleViewCount(dataBo.getArticleViewCount());
                article.setArticleCommentCount(dataBo.getArticleCommentCount());
                article.setArticleDianzanCount(dataBo.getArticleDianzanCount());
            }
            //设置标签数据
            //传入list获取所有标签 封装结果
             articleList= getManyArticleTaggersByIdOrTitle(articles);
        }
        return new Result(articleList,"200","获取成功！");
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
        ArticleSearchDto search = pageData.getSearch();
        getLikeResult = getLikeResult
                .like(!StringUtils.isEmpty(search.getContent()),Article::getArticleContent, search.getContent())
                .like(!StringUtils.isEmpty(search.getTitle()),Article::getArticleTitle, search.getTitle());
                List<Article> articleListBySort=new ArrayList<>();
        if (pageData.getCurrPage()==1
                &&StringUtils.isEmpty(search.getTitle())
                &&StringUtils.isEmpty(search.getContent())
                &&StringUtils.isEmpty(search.getTag())){
            // 读取排序序列
            UserArticleController articleController = uaController.selectById(1);
            Integer[] idsByDiv = DbUtils.getIdsByDiv(articleController.getArticleSortId());
            if (idsByDiv!=null){
                articleListBySort = articleMapper.selectBatchIds(Arrays.asList(idsByDiv));
            }
        }
        //对模糊的结果进行分页
        IPage<Article> iPage = pageData.pageBuild();
        IPage<Article> pageRes = articleMapper.selectPage(iPage, getLikeResult);
        //获取第一次查询的结果
        List<Article> records = pageRes.getRecords();
        // 将分页后的放入排序序列之后
        articleListBySort.addAll(records);
        //去重
        articleListBySort=articleListBySort.stream().distinct().collect(Collectors.toList());
        //去除多余的值
        final int temp=articleListBySort.size();
        if (articleListBySort.size() > pageData.getPageSize()){
            if (temp > pageData.getPageSize()) {
                articleListBySort.subList(pageData.getPageSize(), temp).clear();
            }
        }
        //设置各个文章中的浏览量数据
        for (Article article: articleListBySort) {
            DPLDataBo dataBo = RedisServiceBox.getSingleDPLDataFromRedisById(article.getArticleId());
            article.setArticleViewCount(dataBo.getArticleViewCount());
            article.setArticleCommentCount(dataBo.getArticleCommentCount());
            article.setArticleDianzanCount(dataBo.getArticleDianzanCount());
        }
        //传入list获取所有标签 封装结果
        PageData<Article> resPageData = new PageData<>(getManyArticleTaggersByIdOrTitle(articleListBySort), (int) pageRes.getTotal());
        return new Result(resPageData, StatusMes.PAGE_OK.getCode(), StatusMes.PAGE_OK.getMessage());
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

        return new Result(article, StatusMes.GET_OK.getCode(), StatusMes.GET_OK.getMessage());
    }

    public Result getArticleData(Integer id) {
        //获取文章内容
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Article::getArticleId,
                Article::getArticleContent,
                Article::getArticleCreateTime,
                Article::getArticleUpdateTime,
                Article::getArticleTitle,
                Article::getArticleViewCount);
        wrapper.eq(Article::getArticleId,id);
        Article article = articleMapper.selectOne(wrapper);

        //获取文章标签
        getSingleArticleTaggersByIdOrTitle(article);
        //填充点赞数据
        DPLDataBo dataBo = RedisServiceBox.getSingleDPLDataFromRedisById(Long.valueOf(id));
        article.setArticleViewCount(dataBo.getArticleViewCount());
        article.setArticleCommentCount(dataBo.getArticleCommentCount());
        article.setArticleDianzanCount(dataBo.getArticleDianzanCount());

        return new Result(article, StatusMes.GET_OK.getCode(), StatusMes.GET_OK.getMessage());
    }

    /**
     * 返回时间线文章，降序
     *
     * @return {@link Result}
     */
    @Cacheable(value = "ArticlesByTimeLine")
    public Result getArticlesByTimeLine() {
        LambdaQueryWrapper<Article> a=new LambdaQueryWrapper<>();
        a=a.select(Article::getArticleTitle,Article::getArticleCreateTime,Article::getArticleSummary);
        //读取配置
        UserArticleController articleController = uaController.selectById(1);
        Integer sortId = articleController.getGuiDangSortId();
        if (sortId==1){
            a=a.orderByAsc(Article::getArticleCreateTime);
        }
        else {
            a=a.orderByDesc(Article::getArticleCreateTime);
        }
        List<Article> articles = articleMapper.selectList(a);
        return new Result(articles,StatusMes.GET_OK.getCode(),StatusMes.GET_OK.getMessage());
    }

    //设置文章点赞量
    public Result setDianZan(Integer d, HttpServletRequest request) {
        RedisServiceBox.setDianZanCount(Glotitle,d);
        return new Result(null,StatusMes.UPDATE_OK.getCode(),StatusMes.UPDATE_OK.getMessage());
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
        return new Result(records, StatusMes.GET_OK.getCode(), StatusMes.GET_OK.getMessage());
    }
}
