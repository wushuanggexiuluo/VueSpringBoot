package com.scm.myblog.config.schedulingtaskconfig;

import java.util.concurrent.ScheduledFuture;

/**
 * 计划任务
 *
 * @author 孙超孟
 * @date 2023/04/23
 */
public final class ScheduledTask {

    public volatile ScheduledFuture<?> future;
    /**
     * 取消定时任务
     */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}
