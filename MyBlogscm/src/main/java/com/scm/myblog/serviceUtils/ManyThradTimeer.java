package com.scm.myblog.serviceUtils;

import java.util.Scanner;

//子线程类
public class ManyThradTimeer extends Thread {
    private final Object lock = new Object();
    static int min = 0, sec = 0;
    //阻塞标志（默认关闭）
    private boolean pause = false;

    //将阻塞信号开启
    public void pauseThread() {
        this.pause = true;
    }

    // 暂停信号
    public void clear() {
        min = 0;
        sec = 0;
        show();
    }
    //阻塞方法
    void onPause() {
        synchronized (lock) {
            try {
                lock.wait();//阻塞线程方法
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //恢复方法
    public void resumeThread() {
        this.pause = false;
        synchronized (lock) {

            lock.notify();//恢复线程方法
        }
    }

    void show() {
        System.out.println("当前时间为：" + min + "：" + sec);
    }

    public void mmshow() {
        int mm = (min * 60 + sec) * 100;
        System.out.print("毫秒示数为：" + mm + '\n');
    }



    public void run() {
        super.run();
        while (true) {
            if (pause) {
                onPause();
            }
            try {
                //***此处添加你的代码*** 
                sec++;
                show();
                if (sec == 60) {
                    sec = 0;
                    min++;
                }
                Thread.sleep(1000);

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }//线程运行方法	
}



