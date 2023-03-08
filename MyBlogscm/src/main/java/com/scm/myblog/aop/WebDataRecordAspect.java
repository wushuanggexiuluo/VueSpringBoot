package com.scm.myblog.aop;

import com.scm.myblog.entity.Comment;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.serviceUtils.AdminBlogUtils;
import com.scm.myblog.serviceUtils.RedisServiceBox;
import com.scm.myblog.serviceUtils.UserBlogUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * web数据记录AOP
 *
 * @author Lancer
 * @date 2022/12/09
 */
@Aspect
@Component
@Slf4j
public class WebDataRecordAspect {
    @Autowired
    public RedisTemplate<String,String> rs;

    //设置浏览量增加切点
    @Pointcut("execution(public * com.scm.myblog.controller.ArticleController.getArticleDetail(..))")
    public void ViewAddPointcut() {}
    //设置文章增加切点
    @Pointcut("execution(public * com.scm.myblog.controller.AdminArticleController.addArticles(..))")
    public void AddArticlePointcut() {}
    //设置文章减少切点
    @Pointcut("execution(public * com.scm.myblog.controller.AdminArticleController.removeArticles(..))")
    public void RemoveArticlePointcut() {}
    //设置评论增加切点
    @Pointcut("execution(public * com.scm.myblog.controller.CommentController.setComment(..))")
    public void AddCommentPointcut() {}

    //设置评论减少切点
    @Pointcut("execution(public * com.scm.myblog.controller.AdminCommentController.removeComment(..))")
    public void RemoveCommentPointcut() {}

    /**
     * 浏览量增加切面
     *
     * @param j j
     */
    @Before("ViewAddPointcut()")
    public void ViewAdd(JoinPoint j){
        String sendTitle="";
        Object[] args = j.getArgs();
        if (args != null) {
            sendTitle=(String)args[0];
        }
        RedisServiceBox.addViewCount(sendTitle);
        log.info(sendTitle+"浏览加1");
    }

    //文章DP增加到redis
    @AfterReturning(pointcut ="AddArticlePointcut()",returning = "r")
    public void AddArticle(Object r){
        //获取方法返回值
        Object data=getResultData(r);
        if(data!=null){
            String title=(String)data;
            //增加根据文章的点赞数据到redis
            RedisServiceBox.addDataToRedis(title);
            log.info("新增文章成功！文章标题为 "+title);
        }
    }

    //文章DP减少redis
    @AfterReturning(pointcut ="RemoveArticlePointcut()",returning = "r")
    public void RemoveArticle(Object r){
        //获取方法返回值
        Object data=getResultData(r);
        if(data!=null){
            Long[] ids=(Long[])data;
            //删除文章的点赞数据在redis
            RedisServiceBox.deleteBatchDataToRedis(ids);
            log.info("删除文章成功！文章id为 "+ Arrays.toString(ids));
        }
    }
    //评论量增加在redis
    @AfterReturning(pointcut ="AddCommentPointcut()",returning = "r")
    public void AddComment(Object r){
        //获取方法返回值
        Object data=getResultData(r);
        if(data!=null){
            String title=(String)data;
            //更新Redis中的文章数据
            RedisServiceBox.addCommentCount(title);
            log.info("评论增加成功！文章标题为 "+title);
        }
    }

    //评论量减少在redis
    @AfterReturning(pointcut ="RemoveCommentPointcut()",returning = "r")
    public void RemoveComment(Object r){
        //获取方法返回值
        Object data=getResultData(r);
        if(data!=null){
            List<Comment> commentList = (List<Comment>) (data);
            List<Long> ids = AdminBlogUtils.getArticleIdByCommentList(commentList);
            //更新Redis中的文章数据
            RedisServiceBox.removeCommentCount(ids);
            log.info("评论删除成功！文章id为"+ids);
        }
    }
    public Object getResultData(Object r){
        //获取方法返回值
        Result r1 = (Result) r;
        return r1.getData();
    }
}
