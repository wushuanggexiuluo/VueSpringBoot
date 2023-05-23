package com.scm.myblog.service;

import com.scm.myblog.entity.DOMAIN.Article;
import com.scm.myblog.entity.DTO.ArticleDto;
import com.scm.myblog.entity.DTO.ArticleSearchDto;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.VO.Result;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    /**
     * 分页获取文章可模糊搜素
     *
     * @param pageData 页面数据
     * @return {@link Result}
     */
    Result getArticles(PageDto<Article, ArticleSearchDto> pageData);

    /**
     * 添加文章
     *
     * @param articleDto 文章dto
     * @param rq         HttpServletRequest
     * @return {@link Result}
     */
    Result addArticle(ArticleDto articleDto, HttpServletRequest rq);

    /**
     * 更新文章
     *
     * @param articleDto 文章dto
     * @param rq         HttpServletRequest
     * @return {@link Result}
     */
    Result updateArticles(ArticleDto articleDto, HttpServletRequest rq);

    /**
     * 删除文章
     *
     * @param ids id
     * @return {@link Result}
     */
    Result removeArticles(Long[] ids);

    Result setArticleSort(Integer[] sortIds);

    Result setArticleSortOnJH(Integer sortId);

    Result setArticleSortOnGD(Integer sortId);
}
