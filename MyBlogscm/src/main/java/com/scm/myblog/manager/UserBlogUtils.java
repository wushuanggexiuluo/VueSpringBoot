package com.scm.myblog.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.scm.myblog.common.ErrResult.ErrorResult;
import com.scm.myblog.common.ExceptionLancer.CommonException;
import com.scm.myblog.entity.BO.DPLDataBo;
import com.scm.myblog.entity.CORE.StatusMes;
import com.scm.myblog.entity.DOMAIN.Article;
import com.scm.myblog.entity.DOMAIN.ArticleCategoryRef;
import com.scm.myblog.entity.DOMAIN.Category;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.mapper.ArticleCategoryRefMapper;
import com.scm.myblog.mapper.ArticleMapper;
import com.scm.myblog.mapper.CategoryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class UserBlogUtils {
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

    public static DPLDataBo getSingDataBoFormDb(Long id) {
        LambdaQueryWrapper<Article> a = new LambdaQueryWrapper<>();
        a.eq(Article::getArticleId,id).select(Article::getArticleId, Article::getArticleViewCount, Article::getArticleDianzanCount, Article::getArticleCommentCount);
        Article article = articleMapper.selectOne(a);
        DPLDataBo d = new DPLDataBo();
        BeanUtils.copyProperties(article, d);
        return d;
    }


    /*
    通过标题获取id
     */
    public static Long getIdByTitle(String title) {
        try {
            LambdaQueryWrapper<Article> article = new LambdaQueryWrapper<>();
            article.eq(Article::getArticleTitle, title);
            return articleMapper.selectOne(article).getArticleId();
        } catch (Exception e) {
            ErrorResult build = ErrorResult.build(new ErrorResult(StatusMes.Article_Id_Err.getCode(),StatusMes.Article_Id_Err.getMessage()));
            throw new CommonException(build);
        }
    }

    /*
       获取所有文章id
        */
    public static List<Long> getAllArticleIdFromDb() {
        LambdaQueryWrapper<Article> article = new LambdaQueryWrapper<>();
        article.select(Article::getArticleId);
        List<Article> articles = articleMapper.selectList(article);
        List<Long> ids = new ArrayList<>();
        for (Article article1 : articles) {
            ids.add(article1.getArticleId());
        }
        return ids;
    }

    public static void updateAllLDPData(Article article) {
        articleMapper.updateById(article);
    }

    /*
    获取多篇文章标签，传入参数为List<>
     */
    public static List<Article> getManyArticleTaggersByIdOrTitle(List<Article> records) {
        for (Article article : records) {
            getSingleArticleTaggersByIdOrTitle(article);
        }
        return records;
    }

    public static void getHotArticleIds(List<Long> newIds){
        Map<Long, Long> viewCountSortFromRedis = RedisServiceBox.getMaxViewCountSortFromRedis();
        for (Map.Entry< Long,Long> e: viewCountSortFromRedis.entrySet()){
            newIds.add(e.getKey());
            if(newIds.size() > 10){
                break;
            }
        }
    }

    public static void getHotArticleIdsByDianZan(List<Long> newIds,int SortId){
        Map<Long, Long> DianZanCountSortFromRedis = RedisServiceBox.getMaxDianZanSortFromRedis(SortId);
        Iterator<Map.Entry<Long,Long>> iter = DianZanCountSortFromRedis.entrySet().iterator();
        // 禁止增强for
        while (iter.hasNext()) {
            newIds.add(iter.next().getKey());
            if(newIds.size() > 10){
                break;
            }
        }
    }
    /*
    传入一个article对象，将自动设置对象的标签值
     */
    public static Article getSingleArticleTaggersByIdOrTitle(Article article) {
        //联两个表查询文章标签
        LambdaQueryWrapper<ArticleCategoryRef> articleCategoryRef = new LambdaQueryWrapper<>();
        //根据标题查找id 获取标签id
        Long id = null;
        if (article.getArticleId() != null) {
            id = article.getArticleId();
        }
        else {
            id = UserBlogUtils.getIdByTitle(article.getArticleTitle());
        }
        articleCategoryRef = articleCategoryRef.eq(ArticleCategoryRef::getArticleId, id);
        List<ArticleCategoryRef> refs = categoryRefDao.selectList(articleCategoryRef);

        List<Category> tagsData = new ArrayList<>();
        for (ArticleCategoryRef rs : refs) {
            //获取每一个标签
            LambdaQueryWrapper<Category> categoryQueryWrapper = new LambdaQueryWrapper<>();
            categoryQueryWrapper.eq(Category::getCategoryId, rs.getCategoryId());
            Category category = categoryMapper.selectOne(categoryQueryWrapper);
            //将每一个标签对象放入List tag中
            tagsData.add(category);
            //再将所有相关的标签对象放入List Arcticle中
            article.setCategory(tagsData);
        }
        return article;
    }


    /**
     * 设置默认分页查询
     *
     * @param pto 美国专利商标局
     */
    public static void setDefaultPage(PageDto pto) {
        if (pto.getCurrPage() == null) {
            pto.setCurrPage(1);
        }
        if (pto.getPageSize() == null) {
            pto.setPageSize(10);
        }
    }

    /**
     * 从数据得到所有数据波
     *
     * @return {@link List}<{@link DPLDataBo}>
     */
    public static List<DPLDataBo> getALlDataBoFromDb() {
        LambdaQueryWrapper<Article> s = new LambdaQueryWrapper<>();
        s.select(Article::getArticleId, Article::getArticleViewCount, Article::getArticleDianzanCount, Article::getArticleCommentCount);
        List<Article> articleList = articleMapper.selectList(s);
        List<DPLDataBo> d = new ArrayList<>();
        for (Article article : articleList) {
            DPLDataBo p = new DPLDataBo();
            BeanUtils.copyProperties(article, p);
            d.add(p);
        }
        return d;
    }

    /**
     * 通过标签获取文章列表
     *
     * @param tag1    标签1
     * @param isAdmin 是否是管理
     * @return {@link List}<{@link Article}>
     */
    public static List<Article> getArticleListByTag(String tag1, Boolean isAdmin) {
        //通过标签获取标签id
        Long tagId = categoryMapper.getIdByTagName(tag1);
        //根据标签id获取所有相关文章
        LambdaQueryWrapper<ArticleCategoryRef> c = new LambdaQueryWrapper<>();
        c.eq(ArticleCategoryRef::getCategoryId, tagId);
        List<ArticleCategoryRef> refs = categoryRefDao.selectList(c);
        List<Long> ids = new ArrayList<>();
        for (ArticleCategoryRef o : refs) {
            ids.add(o.getArticleId());
        }
        List<Article> articles = articleMapper.selectBatchIds(ids);
        getManyArticleTaggersByIdOrTitle(articles);

        //不是管理员的话抹除数据
        if (!isAdmin) {
            for (Article o : articles) {
                DPLDataBo data = RedisServiceBox.getSingleDPLDataFromRedisById(o.getArticleId());
                BeanUtils.copyProperties(data, o);
                o.setArticleUserId(null);
                o.setArticleId(null);
                o.setArticleContent(null);
            }
        }
        else {
            for (Article o : articles) {
                DPLDataBo data = RedisServiceBox.getSingleDPLDataFromRedisById(o.getArticleId());
                BeanUtils.copyProperties(data, o);
            }
        }
        return articles;
    }
}
