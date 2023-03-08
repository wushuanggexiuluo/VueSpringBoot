package com.scm.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.scm.myblog.entity.*;
import com.scm.myblog.serviceUtils.AdminBlogUtils;
import com.scm.myblog.serviceUtils.UserBlogUtils;
import com.scm.myblog.mapper.CategoryMapper;
import com.scm.myblog.entity.DTO.TagSearchDto;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.VO.PageData;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.TagService;
import com.scm.myblog.utils.DbUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    public CategoryMapper tag;


    /**
     * 得到所有热门标签
     *
     * @return {@link Result}
     */
    public Result getAllHotTag() {
        //初始化参数
        List<Long> newIds=new ArrayList<>();
        UserBlogUtils.getHotArticleIds(newIds);
        List<String> tagNames = new ArrayList<>();
        //根据id获取标签名
        for (Long id : newIds) {
            List<Long> idsList = AdminBlogUtils.getArtTagIdsList(id);
            List<Category> categories=new ArrayList<>();
            if(idsList.size()>0) {
                 categories= tag.selectBatchIds(idsList);
            }
            for (Category category : categories) {
                tagNames.add(category.getCategoryName());
            }
        }
        //去除重复标签
        List<String> stringList = AdminBlogUtils.toDistinctByListString(tagNames);
        return new Result(stringList, StatusMes.GET_OK.getCode(), StatusMes.GET_OK.getMes());
    }
    /**
     * 把所有标签页
     *
     * @param pto
     * @return {@link Result}
     */
    public Result getAllTagPage(PageDto<Category, TagSearchDto> pto) {
        TagSearchDto search = pto.getSearch();
        System.out.println(pto);
        //设置默认页
        UserBlogUtils.setDefaultPage(pto);
        pto.setCurrPage(DbUtils.getCurePage(pto.getCurrPage(), pto.getPageSize()));
        List<Category> tagPage = tag.getAllTagPage(
                search.getCategoryName(),
                search.getCategoryDescription(),
                pto.getCurrPage(),
                pto.getPageSize());
        Integer i = tag.getAllTagCount(
                search.getCategoryName(),
                search.getCategoryDescription());
        return new Result(new PageData<Category>(tagPage, i), StatusMes.PAGE_OK.getCode(), StatusMes.PAGE_OK.getMes());
    }

    /**
     * 删除标签
     *
     * @param ids id
     * @return {@link Result}
     */
    public Result removeTag(Integer[] ids) {
        int i = 0;
        if (ids.length > 1) {
            i = tag.deleteBatchIds(Arrays.asList(ids));
        } else {
            i = tag.deleteById(ids[0]);
        }
        //同时删除对应文章的标签，和文章标签映射表的数据

        AdminBlogUtils.deleteArticleRefTag(Arrays.asList(ids));
        return new Result(null, i > 0 ? StatusMes.DELETE_OK.getCode() : StatusMes.DELETE_ERR.getCode(),
                i > 0 ? StatusMes.DELETE_OK.getMes() : StatusMes.DELETE_ERR.getMes());

    }

    /**
     * 更新标签
     *
     * @param tag1 标签1
     * @return {@link Result}
     */
    public Result updateTag(Category tag1) {
        int i = tag.updateById(tag1);
        return new Result(null, i > 0 ? StatusMes.UPDATE_OK.getCode() : StatusMes.UPDATE_ERR.getCode(), i > 0 ? StatusMes.UPDATE_OK.getMes() : StatusMes.UPDATE_ERR.getMes());

    }

    /**
     * 添加标签
     *
     * @param tag1 标签1
     * @return {@link Result}
     */
    public Result AddTag(Category tag1) {
        Category one = tag.selectOne(new LambdaQueryWrapper<Category>().eq(Category::getCategoryName, tag1.getCategoryName()));
        if (one == null) {
            int i = tag.insert(tag1);
            return new Result(null, i > 0 ? StatusMes.SAVE_OK.getCode() : StatusMes.SAVE_ERR.getCode(),
                    i > 0 ? StatusMes.SAVE_OK.getMes() : StatusMes.SAVE_ERR.getMes());
        } else {
            return new Result(null, StatusMes.SAVE_ERR.getCode(), "标签名不能重复！");
        }

    }
    public Result getArticleByTag(String tag1) {
        List<Article> list = UserBlogUtils.getArticleListByTag(tag1, false);
        return new Result(list,StatusMes.GET_OK.getCode(), StatusMes.GET_OK.getMes());
    }

    //获取所有标签数据
    public Result getAllTag(PageDto<Category, TagSearchDto> pto) {
        LambdaQueryWrapper<Category> c=new LambdaQueryWrapper<>();
        c.select(Category::getCategoryName,Category::getCategoryIcon,Category::getCategoryDescription)
                .like(Category::getCategoryName,pto.getSearch().getCategoryName())
                .like(Category::getCategoryDescription,pto.getSearch().getCategoryDescription());
        List<Category> categories = tag.selectList(c);
        return new Result(categories, StatusMes.PAGE_OK.getCode(), StatusMes.PAGE_OK.getMes());
    }
}
