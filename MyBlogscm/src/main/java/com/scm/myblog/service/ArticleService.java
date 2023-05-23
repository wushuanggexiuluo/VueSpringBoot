package com.scm.myblog.service;


import com.scm.myblog.entity.DOMAIN.Article;
import com.scm.myblog.entity.DTO.ArticleSearchDto;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.DTO.SouCangDto;
import com.scm.myblog.entity.VO.Result;

import javax.servlet.http.HttpServletRequest;

public interface ArticleService {

    /**
     * 获取页面数据
     *
     * @param pageData      页面数据
     * @param isFindContent 是否查找文章的内容
     * @return {@link Result}
     */
    Result getArticlePage(PageDto<Article, ArticleSearchDto> pageData, Boolean isFindContent);

    /**
     * 获取单片文章的所有数据
     *
     * @param title 标题
     * @return {@link Result}
     */
    Result getArticleData(String title);

    /**
     * 搜索文章通过内容，标题
     *
     * @param search 搜索
     * @return {@link Result}
     */
    Result getArticlesBySearch(String search);

    /**
     * 设置点赞数据
     *
     * @param d       d
     * @param request 请求
     * @return {@link Result}
     */
    Result setDianZan(Integer d, HttpServletRequest request);

    /**
     * 通过时间线获取文章
     *
     * @return {@link Result}
     */
    Result getArticlesByTimeLine();
    /**
     * 获取所有精品文章
     *
     * @return {@link Result}
     */
     Result getAllBoutique();

    Result setShouCang(SouCangDto uid);

    Result getShouCangData(Integer uid);
}
