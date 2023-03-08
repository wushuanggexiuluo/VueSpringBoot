package com.scm.myblog.aop;

import com.scm.myblog.entity.Article;
import com.scm.myblog.entity.Category;
import com.scm.myblog.entity.Code;
import com.scm.myblog.entity.Tips;
import com.scm.myblog.entity.VO.PageData;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.exception.DefinitionException;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 图像处理ASP
 *
 * @author 孙超孟
 * @date 2023/01/09
 */
@Aspect
@Component
public class PictureProcessingAspect {

    //标签图片
    @Pointcut("execution(public * com.scm.myblog.service.TagService.getAllTag(..))")
    public void GetAllTagImg(){};
    //文章封面图片
    @Pointcut("execution(public * com.scm.myblog.service.ArticleService.getArticlePage(..))")
    public void GetAllArticleImg(){};

    //精品文章封面
    @Pointcut("execution(public * com.scm.myblog.service.ArticleService.getAllBoutique(..))")
    public void GetAllBoutiqueImg(){};

    //文章封面图片切面
    @AfterReturning(returning = "r",pointcut = "GetAllArticleImg()")
    public Result doAfter2(Object r){
        //获取方法返回值
        Result r1 =getReturnResult(r);
        PageData<Article> pd=null;
        //暂存需要更改的对象
        List<Article> lists = null;
        if (r1!=null)
        {
            Object data = r1.getData();
            if (data!=null) {
                pd =(PageData<Article>)data;
                lists = pd.getPageDataList();
                //重新设置图标尺寸
                for (Article c:lists) {
                    String temp=c.getArticleThImg();
                    if(temp != null){
                    if (!temp.contains("@th7")){
                        c.setArticleThImg(temp+"@th7");
                    }}
                }
            }
        }
        //将重新设置的对象返回
        assert pd != null;
        return new Result(new PageData<Article>(lists,pd.getTotal()), Code.PAGE_OK, Tips.PAGE_OK);
    }


    //精品文章封面图片切面
    @AfterReturning(returning = "r",pointcut = "GetAllBoutiqueImg()")
    public Result doAfter3(Object r){
        Result r1 =getReturnResult(r);
        //暂存需要更改的对象
        List<Article> lists = null;
        if (r1!=null)
        {
            Object data = r1.getData();
            if (data!=null) {
                lists=(List<Article>) data;
                //重新设置图标尺寸
                for (Article c:lists) {
                    c.setArticleThImg(c.getArticleThImg()+"@th7");
                }
            }
        }
        //将重新设置的对象返回
        return new Result(lists,Code.GET_OK,Tips.GET_OK);
    }
    //标签图片切面
    @AfterReturning(returning = "r",pointcut = "GetAllTagImg()")
    public Result doAfter1(Object r){
        //获取方法返回值
        Result r1 =getReturnResult(r);
        //暂存需要更改的对象
        List<Category> lists = null;
        if (r1!=null)
        {
            Object data = r1.getData();
            if (data!=null) {
                lists=(List<Category>) data;
                //重新设置图标尺寸
                for (Category c:lists) {
                    c.setCategoryIcon(c.getCategoryIcon()+"@th7");
                }
            }
        }
        //将重新设置的对象返回
        return new Result(lists, Code.PAGE_OK, Tips.PAGE_OK);
    }

    //获取Result形式 的返回值
    public Result getReturnResult(Object r){
        if (r!=null){
            //获取方法返回值
            return (Result) r;
        }
        else {
            throw new DefinitionException(11111,"返回值异常");
        }
    }
}
