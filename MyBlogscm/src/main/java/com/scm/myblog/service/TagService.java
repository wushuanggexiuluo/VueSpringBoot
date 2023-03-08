package com.scm.myblog.service;

import com.scm.myblog.entity.Category;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.DTO.TagSearchDto;
import com.scm.myblog.entity.VO.Result;

public interface TagService {
    /**
     * 得到所有热门标签
     *
     * @return {@link Result}
     */
     Result getAllHotTag();

    /**
     * 获取所有标签分页加模糊搜索
     *
     * @param pto 
     * @return {@link Result}
     */
     Result getAllTagPage(PageDto<Category, TagSearchDto> pto);

    /**
     * 删除标签
     *
     * @param ids id
     * @return {@link Result}
     */
    Result removeTag(Integer[] ids);

    /**
     * 更新标签
     *
     * @param tag1 标签对象
     * @return {@link Result}
     */
    Result updateTag(Category tag1);

    /**
     * 添加标签
     *
     * @param tag1 标签
     * @return {@link Result}
     */
    Result AddTag(Category tag1) ;

    /**
     * 获取文章信息通过标签
     *
     * @param tag1 标签对象
     * @return {@link Result}
     */
    Result getArticleByTag(String tag1);

    /**
     * 获取所有标签不分页
     *
     * @param pto 
     * @return {@link Result}
     */
    Result getAllTag(PageDto<Category, TagSearchDto> pto);
    }
