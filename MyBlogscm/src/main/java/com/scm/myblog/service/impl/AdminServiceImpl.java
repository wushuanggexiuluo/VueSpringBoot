package com.scm.myblog.service.impl;

import com.scm.myblog.entity.CORE.StatusMes;
import com.scm.myblog.entity.DOMAIN.Article;
import com.scm.myblog.entity.DOMAIN.ArticleCategoryRef;
import com.scm.myblog.entity.DOMAIN.Category;
import com.scm.myblog.entity.DOMAIN.UserArticleController;
import com.scm.myblog.entity.DTO.ArticleDto;
import com.scm.myblog.entity.DTO.ArticleSearchDto;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.manager.AdminBlogManager;
import com.scm.myblog.manager.RedisServiceBox;
import com.scm.myblog.manager.ServiceCommonManager.DeleteManager;
import com.scm.myblog.manager.UserBlogUtils;
import com.scm.myblog.mapper.ArticleCategoryRefMapper;
import com.scm.myblog.mapper.ArticleMapper;
import com.scm.myblog.mapper.CategoryMapper;
import com.scm.myblog.mapper.UserArticleControllerMapper;
import com.scm.myblog.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.scm.myblog.utils.DbUtils.setIdsByDiv;


@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    private static CategoryMapper categoryMapper;

    @Autowired
    private CategoryMapper categoryMapperA;
    @Autowired
    private UserArticleControllerMapper uaControllerMapper;


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
            Article article=new Article();
            //插入
            //属性copy
            BeanUtils.copyProperties(articleDto,article);
            log.info("略缩图为："+article.getArticleThImg());
            int i = articleMapper.insert(article);
            //过滤标签
            ArticleDto articleDto1 = AdminBlogManager.filterTaggers(articleDto);
            //----插入标签表-----
            AdminBlogManager.insertNewTag(articleDto1.getCategory());
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
            return new Result(articleDto.getArticleTitle(), i>0? StatusMes.SAVE_OK.getCode():StatusMes.SAVE_ERR.getCode(), i>0?StatusMes.SAVE_OK.getMessage():StatusMes.SAVE_ERR.getMessage());
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
        //过滤标签
        ArticleDto articleDto1 = AdminBlogManager.filterTaggers(articleDto);
        //----插入标签表-----
        List<Long> longIds = AdminBlogManager.insertNewTag(articleDto1.getCategory());
        //属性copy
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        //通过老标题获取id
        Long artId = UserBlogUtils.getIdByTitle(articleDto.getOldarticleTitle());
        //----更新文章表-----
        AdminBlogManager.updateArticle(article,artId);
        //-----更新文章标签映射表-----
        AdminBlogManager.updateCategoryRef(longIds,artId,article.getCategory());
        return new Result(null, StatusMes.UPDATE_OK.getCode(), StatusMes.UPDATE_OK.getMessage());
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
        return DeleteManager.deleteBatchIds(isRemoveSuccess,ids);
    }


    @Override
    public Result setArticleSort(Integer[] sortIds) {
        UserArticleController u=new UserArticleController();
        u.setUid(9);
        u.setArticleSortId(setIdsByDiv(sortIds));
        return commonSetSort(u);
    }

    @Override
    public Result setArticleSortOnJH(Integer sortId) {
        if (sortId!=1&&sortId!=2){
            return new Result(null,"200","参数错误！");
        }
        UserArticleController u=new UserArticleController();
        u.setUid(9);
        u.setJingHuaSortId(sortId);
        return commonSetSort(u);
    }


    public Result commonSetSort(UserArticleController u){
        UserArticleController articleController = uaControllerMapper.selectById(1);
        int i;
        if(articleController==null){
            i=uaControllerMapper.insert(u);
        }
        else {
            u.setId(1);
            i=uaControllerMapper.updateById(u);
        }
        Result result = new Result();
        result.setCode("200");
        if (i>0){
            result.setMessage("设置成功!");
        }
        else {
            result.setMessage("设置失败");
        }
        return result;
    }
    @Override
    public Result setArticleSortOnGD(Integer sortId) {
        if (sortId!=1&&sortId!=2){
            return new Result(null,"200","参数错误！");
        }
        UserArticleController u=new UserArticleController();
        u.setUid(9);
        u.setGuiDangSortId(sortId);
        return commonSetSort(u);
    }

}
