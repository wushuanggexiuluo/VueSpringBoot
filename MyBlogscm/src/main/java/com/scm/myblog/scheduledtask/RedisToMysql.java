package com.scm.myblog.scheduledtask;

import com.scm.myblog.common.ExceptionLancer.CommonException;
import com.scm.myblog.entity.CORE.Code;
import com.scm.myblog.entity.CORE.Tips;
import com.scm.myblog.entity.DOMAIN.Article;
import com.scm.myblog.manager.CacheManage.CaffeineManage;
import com.scm.myblog.manager.UserBlogUtils;
import com.scm.myblog.mapper.ArticleMapper;
import com.scm.myblog.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.concurrent.*;

import static com.scm.myblog.manager.RedisServiceBox.*;


@Slf4j
@Component
public class RedisToMysql implements RedisToDB {

    public static ArticleMapper articleMapper;
    private static RedisTemplate<String, String> rt;
    public static RedisTemplate<Object, Object> rt2;

    @Autowired
    private ArticleMapper articleMapperA;

    @Autowired
    public RedisTemplate<String, String> rtA;
    @Autowired
    public RedisTemplate<Object, Object> rtB;


    @PostConstruct
    private void init() {
        articleMapper = articleMapperA;
        rt = rtA;
        rt2=rtB;
    }
    static int i=0;

    public static ServletContext servletContext;


    public static RedisToMysql build() {
        return new RedisToMysql();
    }
    // 写入数据库通用方法
    @CacheEvict(value = CaffeineManage.BoutiqueArticles)
    public void writeDb(){
        //从数据库获取所有文章id
        List<Long> allId = UserBlogUtils.getAllArticleIdFromDb();
        //获取所有新的数据
        for (Long id : allId) {
            List<String> data = rt.opsForList().range(CreateArticleIdOnRedis(id), 0, -1);
            Article article = new Article();
            article.setArticleId(id);
            assert data != null;
            article.setArticleViewCount(Long.parseLong(data.get(2)));
            article.setArticleDianzanCount(Long.parseLong(data.get(1)));
            article.setArticleCommentCount(Long.parseLong(data.get(0)));
            articleMapper.updateById(article);
        }
    }
    // 获取用户输入的写入时间编号，如果用户没有输入则默认1
    public int getI(){
        // 最大可选择数
        int maxNumber=4;
        // 给用户输入的时间
        int timeByUserInput=100;
        // 提示消息
        String Tips="请选择Redis写入Mysql的时间\n" +
                "1 每1分钟写入一次\n" +
                "2 每60分钟写一次\n" +
                "3 每12小时写入一次\n" +
                "4 在关闭程序时写入";
        Scanner input = new Scanner(System.in);
        final Integer[] i = {0};
        // 多线程任务存储Map
        ConcurrentHashMap<String, ScheduledFuture<?>> taskMap = new ConcurrentHashMap<>(4);
        // 创建ScheduleExecutorService 线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
        //开启输入任务
        ScheduledFuture<?> inputFuture = pool.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //不是需要的i则进入输入
                if (i[0] < 1 || i[0] > maxNumber) {
                    log.info(Tips);
                    //这里会阻塞inputFuture线程
                    String temp = input.next();
                    try {
                        i[0] = Integer.parseInt(temp);
                        log.info(String.valueOf(i[0]));
                    } catch (Exception e) {
                        log.info("请重新输入！");
                    }
                }
                //已经输入的就终止线程inputFuture即可
                else {
                    taskMap.get("inputFuture").cancel(true);
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
        taskMap.put("inputFuture", inputFuture);
        try {
            Thread.sleep(timeByUserInput);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (i[0] < 1 || i[0] > maxNumber) {
            //默认第一种写入方法
            i[0] = 1;
            taskMap.get("inputFuture").cancel(true);
            log.info("默认1分钟写一次");
        }
        return i[0];
    }
    //设置Redis的持久化时间
    public RedisToMysql setRedisWriteDBTime(ServletContextEvent sce){
        i = getI();
        servletContext= WebUtils.getServletContext(sce);
        switch (i) {
            case 1:
                servletContext.setAttribute("writeDb",1);
                break;
            case 2:
                servletContext.setAttribute("writeDb",2);
                break;
            case 3 :
                servletContext.setAttribute("writeDb",3);
                break;
            default :
                servletContext.setAttribute("writeDb",1);
                break;
        }
        return this;
    }

    //Redis定时写入数据库
    //每60分钟写入一次数据库
    @Scheduled(cron = "0 0/60 * * * *")
    @Async
    public void writeDbWhen60Minute(){
        if (i==1){
            log.info("writeDbWhen60Minute写入数据库");
            writeDb();
        }
    }

    @Scheduled(cron = "0 0/1 * * * *")
    @Async
    public void writeDbWhen1Minute() {
        if (i==3){
            log.info("writeDbWhen1Minute写入数据库");
            writeDb();
        }
    }
    //Redis在夜晚写入数据库
    @Scheduled(cron = "0 0 12 * * *")
    @Async
    public void writeDbWhen12Hour(){
        if (i==2){
            log.info("writeDbWhen12Hour写入数据库");
            writeDb();
        }
    }
    //摧毁Redis时 写入mysql数据库
    public void writeDbWhenDestroyRedis() {
        writeDb();
    }


    // 通过Redis写入数据库
    public void writeDbFromRedis(){
        String s = rt.opsForValue().get("isInit");
        if (Objects.equals(s, "1")) {
            writeDb();
        }
        else {
            throw new CommonException(Code.InsertMySQLFromRedis_ERR, Tips.InsertMySQLFromRedis_ERR);
        }
    }
}
