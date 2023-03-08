package com.scm.myblog.config;

public class ApiConfig {

    //无需权限即可访问的
    public static String [] NoAuthApi=new String[] {
            //登陆注册
            "/Blog/Login",
            "/Blog/Register",
            //标签
            "/Blog/getAllHotTag",
            "/Blog/getAllTag",
            "/Blog/getTagArticle/",
            //留言
            "/Blog/getAllMessage",
            "/Blog/setMessage",
            //友链
            "/Blog/getAllLink",
            "/Blog/addLink",
            //评论
            "/Blog/setComment",
            "/Blog/getComment/",
            //精品
            "/Blog/getAllBoutique",
            //文章
            "/Blog/setDianZan/",
            "/Blog/getPageData",
            "/Blog/getArticles/",
            "/Blog/getArticlesByTimeLine",
            "/Blog/getArticlesSearch/",
            "/Blog/getAllHotTag",
            //图片上传
            "/Upload",
            //测试连接
            "cs/get",
            "cs/get2",
            "cs/csuser"
    };
}
