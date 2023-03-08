package com.scm.myblog.serviceUtils;

import java.util.Scanner;
import java.util.TimerTask;
import java.util.concurrent.*;

public class AnsyUtils {
    public static  int getI(){
        Scanner scaner = new Scanner(System.in);
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
                if (i[0] < 1 || i[0] > 3) {
                    System.out.println("请选择Redis写入Mysql的时间\n" +
                            "1 每60分钟写入一次\n" +
                            "2 每天晚上 12点写入一次\n" +
                            "3 在关闭程序时写入\n");
                    //这里会阻塞inputFuture线程
                    String temp = scaner.next();
                    try {
                        i[0] = Integer.parseInt(temp);
                        System.out.println(i[0]);
                    } catch (Exception e) {
                        System.out.println("请重新输入！");
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
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (i[0] < 1 || i[0] > 3) {
            //默认第一种写入方法
            i[0] = 2;
            taskMap.get("inputFuture").cancel(true);
            System.out.println("默认1天写一次");
        }
        return i[0];
    }
}
