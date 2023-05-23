package com.scm.myblog.runner;

import com.scm.myblog.manager.RedisServiceBox;
import com.scm.myblog.scheduledtask.RedisToMysql;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;


@Component
public class RedisInit implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
//        try {
            // 初始化点赞浏览数据
            RedisServiceBox.init_Redis();
            //初始化写入方式
            ServletContext se = RedisToMysql.servletContext;
            int i = (Integer) se.getAttribute("writeDb");
            RedisToMysql task = new RedisToMysql();
            switch (i) {
                case 1:
                    task.writeDbWhen1Minute();
                    break;
                case 2:
                    task.writeDbWhen60Minute();
                    break;
                case 3:
                    task.writeDbWhen12Hour();
                    break;
                default:
                    break;
            }
//        } catch (Exception e) {
//            throw new CommonException(StatusMes.Redis_ERR.getCode(), StatusMes.Redis_ERR.getMessage());
//        }
    }
}
