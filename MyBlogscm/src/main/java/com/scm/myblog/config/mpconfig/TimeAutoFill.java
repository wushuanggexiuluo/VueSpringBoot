package com.scm.myblog.config.mpconfig;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

//自动填充时间配置
@Component
public class TimeAutoFill implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //将所有需要插入更新的时间写在这里，第一个参数为需要自动填充的字段名
        this.setFieldValByName("commentCreateTime",new Timestamp(new Date().getTime()), metaObject);
        this.setFieldValByName("articleCreateTime",new Timestamp(new Date().getTime()), metaObject);

        this.setFieldValByName("messageCreateTime",new Timestamp(new Date().getTime()), metaObject);
        this.setFieldValByName("linkCreateTime",new Timestamp(new Date().getTime()), metaObject);

        this.setFieldValByName("createTime",new Timestamp(new Date().getTime()), metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Timestamp(new Date().getTime()), metaObject);
        this.setFieldValByName("linkUpdateTime", new Timestamp(new Date().getTime()), metaObject);
        this.setFieldValByName("articleUpdateTime", new Timestamp(new Date().getTime()), metaObject);
    }
}
