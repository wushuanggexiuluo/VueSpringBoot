package com.scm.myblog.service.authservice;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.scm.myblog.common.ExceptionLancer.CommonException;
import com.scm.myblog.entity.BO.LoginUser;
import com.scm.myblog.entity.CORE.StatusMes;
import com.scm.myblog.entity.DOMAIN.User;

import com.scm.myblog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //判断用户名是否存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        //用户不存在
        if(user == null){
           throw new CommonException(StatusMes.USER_NO_REGISTER.getCode(), StatusMes.USER_NO_REGISTER.getMessage());
        }
        //为用户封装权限
        //封装权限信息
        List<String> authList=userMapper.getAuthorityParams(user.getUserId());
        return new LoginUser(user,authList);
    }

}
