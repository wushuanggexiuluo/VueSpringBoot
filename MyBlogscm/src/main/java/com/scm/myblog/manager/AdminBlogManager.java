package com.scm.myblog.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.scm.myblog.entity.DOMAIN.Article;
import com.scm.myblog.entity.DOMAIN.ArticleCategoryRef;
import com.scm.myblog.entity.DOMAIN.Category;
import com.scm.myblog.entity.DOMAIN.Comment;
import com.scm.myblog.mapper.ArticleCategoryRefMapper;
import com.scm.myblog.mapper.ArticleMapper;
import com.scm.myblog.mapper.CategoryMapper;
import com.scm.myblog.entity.DTO.ArticleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@Slf4j
public class AdminBlogManager {
    private static ArticleCategoryRefMapper categoryRefDao;
    private static CategoryMapper categoryMapper;
    private static ArticleMapper articleMapper;
    @Autowired
    private ArticleCategoryRefMapper categoryRefDaoA;
    @Autowired
    private CategoryMapper categoryMapperA;
    @Autowired
    private ArticleMapper articleMapperA;


    //在进入此类中时，此方法会被优先调用
    @PostConstruct
    private void init() {
        categoryRefDao = categoryRefDaoA;
        categoryMapper = categoryMapperA;
        articleMapper = articleMapperA;
    }


    /**
     * 插入新标签
     *
     * @param categories 类别
     * @return {@link List}<{@link Long}>
     */
    public static List<Long> insertNewTag(List<Category> categories) {
        //存入新插入的标签id
        List<Long> tagIds = new ArrayList<>();
        if (categories != null) {
            //标签去重
            toDistinctList(categories);
            //插入新标签
            //查询数据库获取所有标签对象
            List<Category> dbAllTag = categoryMapper.selectList(null);
            //获取数据库所有标签对象的标签名
            List<String> dbAllTagName = new ArrayList<>();
            for (Category category : dbAllTag) {
                dbAllTagName.add(category.getCategoryName());
            }
            //判断是不是新增的标签
            try {
                for (Category ca : categories) {
                    //如果不是存在的则为新增插入即可
                    if (!dbAllTagName.contains(ca.getCategoryName())) {
                        categoryMapper.insert(ca);
                        //获取新插入的标签的id
                        tagIds.add(categoryMapper.getIdByTagName(ca.getCategoryName()));
                    }
                }
            }
            catch (Exception e){
                log.info(e.getMessage());
            }
        }
        return tagIds;
    }


    /*
    更新文章表
     */
    public static void updateArticle(Article article, Long artId) {
        LambdaUpdateWrapper<Article> articleWrapper = new LambdaUpdateWrapper<>();
        articleWrapper.eq(Article::getArticleId, artId)
                .set(Article::getArticleContent, article.getArticleContent())
                .set(Article::getArticleUpdateTime, article.getArticleUpdateTime())
                .set(Article::getArticleSummary, article.getArticleSummary())
                .set(Article::getArticleTitle, article.getArticleTitle());
        articleMapper.update(article, articleWrapper);
    }

    /**
     * 得到当前文章的标签id列表
     *
     * @param artId 文章id
     * @return {@link List}<{@link Long}>
     */
    public static List<Long> getArtTagIdsList(Long artId) {
        //查询原来的所有标签的id
        LambdaQueryWrapper<ArticleCategoryRef> ref = new LambdaQueryWrapper<>();
        ref.eq(ArticleCategoryRef::getArticleId, artId).select(ArticleCategoryRef::getCategoryId);
        List<ArticleCategoryRef> refs = categoryRefDao.selectList(ref);
        //暂存当前文章的标签id
        List<Long> categoryIds = new ArrayList<>();
        for (ArticleCategoryRef o : refs) {
            categoryIds.add(o.getCategoryId());
        }
        return categoryIds;
    }

    /**
     * 删除类别裁判
     *
     * @param artId          艺术id
     * @param newCategory    新类别
     * @param oldCategoryIds 老类别id
     */
    public static void removeCategoryRef(Long artId, List<Category> newCategory, List<Long> oldCategoryIds) {
        //获取需要更新文章的前台传过来的标签id
        List<Long> newCategoryids = new ArrayList<>();
        if (newCategory != null) {
            for (Category ta : newCategory) {
                newCategoryids.add(categoryMapper.getIdByTagName(ta.getCategoryName()));
            }
            //不存在则删除
            for (Long oldId : oldCategoryIds) {
                if (!newCategoryids.contains(oldId)) {
                    LambdaQueryWrapper<ArticleCategoryRef> a = new LambdaQueryWrapper<>();
                    a.eq(ArticleCategoryRef::getCategoryId, oldId).eq(ArticleCategoryRef::getArticleId, artId);
                    categoryRefDao.delete(a);
                }
            }
        }
    }

    /*
    更新标签映射表
     */
    public static void updateCategoryRef(List<Long> longIds, Long artId, List<Category> newCategory) {
        //获取当前文章标签的id的List集合
        List<Long> oldCategoryIds = getArtTagIdsList(artId);
        //说明是新增的文章的，则直接插入文章标签映射表
        if (oldCategoryIds.size() < 1) {
            assert newCategory!=null;
            for (Category c : newCategory) {
                ArticleCategoryRef a = new ArticleCategoryRef();
                a.setArticleId(artId);
                a.setCategoryId(categoryMapper.getIdByTagName(c.getCategoryName()));
                categoryRefDao.insert(a);
            }
        }
        else {
            //说明插入了新的标签
            //比对当前的标签id

            for (Category c : newCategory) {
                Long t = categoryMapper.getIdByTagName(c.getCategoryName());
                if (!oldCategoryIds.contains(t)) {
                    categoryRefDao.insert(new ArticleCategoryRef(artId, t));
                }
            }

        }
        //说明没有插入新的标签，则进行重复标签移除工作
        removeCategoryRef(artId, newCategory, oldCategoryIds);
    }

    public static ArticleDto filterTaggers(ArticleDto article) {
        //获取当前数据的标签
        List<Category> newCategories = article.getCategory();
        if (newCategories != null) {
            //去重标签名中的空格
            for (Category c1 : newCategories) {
                c1.setCategoryName(c1.getCategoryName().trim());
            }
        }
        return article;
        //查找当前文章在，数据库中原来的标签
    }


    /**
     * 删除文章标签映射表的数据
     *
     * @param ids id
     */
    public static void deleteArticleRefTag(List<Integer> ids){
        for (Integer id : ids) {
            LambdaQueryWrapper<ArticleCategoryRef> ar=new LambdaQueryWrapper<>();
            ar.eq(ArticleCategoryRef::getCategoryId,id);
            categoryRefDao.delete(ar);
        }
    }

    /*
    传入 List<Category> ,去除重复
     */
    public static List<Category> toDistinctList(List<Category> list) {
    //  标签对象,根据标签名去除重复值
        Set<Category> Set1 = new TreeSet<>(Comparator.comparing(Category::getCategoryName));
        Set1.addAll(list);
        list.clear();
        list.addAll(Set1);
        return list;
    }

    public static List<String> toDistinctByListString(List<String> list) {
    //  标签对象,根据标签名去除重复值
        Set<String> s = new TreeSet<>(list);
        list.clear();
        list.addAll(s);
        return list;
    }


    /**
     * 获取评论文章标题列表
     *
     * @param cs cs
     * @return {@link List}<{@link String}>
     */
    public static List<Long> getArticleIdByCommentList(List<Comment> cs){
      List<Long> ids=new ArrayList<>();
       for (Comment c:cs) {
           ids.add(c.getCommentArticleId());
       }
       return ids;
    }



}
