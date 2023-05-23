package com.scm.myblog.scheduledtask;

import javax.servlet.ServletContextEvent;

public interface RedisToDB {
    RedisToDB setRedisWriteDBTime(ServletContextEvent sce);
}
