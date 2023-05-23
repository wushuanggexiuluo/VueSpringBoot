package com.scm.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scm.myblog.entity.DOMAIN.UserInfo;
import com.scm.myblog.service.UserInfoService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
* @author 孙超孟
* @description 针对表【user_info(用户基本信息表)】的数据库操作Service实现
* @createDate 2023-04-26 11:01:16
*/
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Override
    public boolean saveBatch(Collection<UserInfo> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<UserInfo> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<UserInfo> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(UserInfo entity) {
        return false;
    }

    @Override
    public UserInfo getOne(Wrapper<UserInfo> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<UserInfo> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<UserInfo> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<UserInfo> getBaseMapper() {
        return null;
    }

    @Override
    public Class<UserInfo> getEntityClass() {
        return null;
    }
}




