package com.scm.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.scm.myblog.common.ExceptionLancer.CommonException;
import com.scm.myblog.entity.CORE.StatusMes;
import com.scm.myblog.entity.DOMAIN.User;
import com.scm.myblog.mapper.*;
import com.scm.myblog.entity.BO.LoginUser;
import com.scm.myblog.entity.DTO.LoginDto;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.LoginService;
import com.scm.myblog.utils.DbUtils;
import com.scm.myblog.utils.JWTUtils;
import com.scm.myblog.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserMapper userDao;
    /**
     * 登录/注册
     *
     * @param loginDto 登录dto
     * @return {@link Result}
     */
    public Result Login(LoginDto loginDto) {
        //判断用户名密码是否正确
        UsernamePasswordAuthenticationToken ut=new UsernamePasswordAuthenticationToken(loginDto.getUserName(),loginDto.getUserPassword());
        Authentication authenticate = null;
        try {
             authenticate = manager.authenticate(ut);
        }
//        org.springframework.security.authentication.InternalAuthenticationServiceException

        //用户名不存在
        catch (InternalAuthenticationServiceException e){
            throw new CommonException(StatusMes.USER_NO_REGISTER.getCode(), StatusMes.USER_NO_REGISTER.getMessage());
        }
        //密码不正确
        if (Objects.isNull(authenticate)){
            throw new CommonException(StatusMes.PASSWORD_ERR.getCode(), StatusMes.PASSWORD_ERR.getMessage());
        }
        //正确生成token
        LoginUser l=(LoginUser)authenticate.getPrincipal();
        String UserId = l.getU().getUserId().toString();
        String token = JWTUtils.createToken(UserId);
        //创建token键值对将数据返回
        HashMap<String,String> map = new HashMap<>();
        map.put("token",token);
        log.info(JWTUtils.validateToken(token));
        //登陆成功将用户数据放入redis
        RedisUtils.setDataFromRedis("login:"+UserId,l);
        Result result = new Result();
        result.setData(map);
        result.setMessage("登陆成功！");
        if (loginDto.getUserName().equals("admin"))
        {
            result.setCode(StatusMes.PASSWORD_OK_ADMIN.getCode());
        }
        else {
            result.setCode(StatusMes.PASSWORD_OK.getCode());
        }
        return result;
    }

    /**
     * 是否存在用户
     *
     * @param loginDto 登录dto
     * @return {@link Result}
     */
    public Result isExistUser(LoginDto loginDto) {
        String resultCode;
        String resultMes = "";
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, loginDto.getUserName());
        if (userDao.selectOne(queryWrapper) == null) {
            //用户名不存在
            resultCode = StatusMes.USER_NO_REGISTER.getCode();
            resultMes = "用户名不存在";
        } else {
            //用户名存在
            resultCode = StatusMes.USER_OK.getCode();
            resultMes = "用户名存在";
        }
        return new Result(null, resultCode, resultMes);
    }

    @Override
    public Result Register(LoginDto loginDto) {
        //查看用户是否存在
        LambdaQueryWrapper<User> qw=new LambdaQueryWrapper<>();
        qw.eq(User::getUserName,loginDto.getUserName());
        User user = userDao.selectOne(qw);
        //不存在则注册
        Result result = new Result();
        if (user == null) {
            User u=new User();
            BeanUtils.copyProperties(loginDto, u);
            //加密密码
            u.setUserPassword(DbUtils.EncodePassword(u.getUserPassword()));
            int i = userDao.insert(u);
            result.setCode(i>0?StatusMes.REGISTER_OK.getCode():StatusMes.REGISTER_ERR.getCode());
            result.setMessage(StatusMes.REGISTER_OK.getMessage());
        }
        else {
            result.setCode(StatusMes.USER_OK.getCode());
            result.setMessage(StatusMes.USER_OK.getMessage());
        }
        return result;
    }
    @Override
    public  Result logout() {
        //获取token
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        //获取id
        LoginUser o = (LoginUser) authentication.getPrincipal();
        String id = o.getU().getUserId().toString();
        //删除redis中的id信息
        RedisUtils.deleteDataFromRedis("login:"+id);
        return new Result(null,StatusMes.Cancel_OK.getCode(),StatusMes.Cancel_OK.getMessage());
    }
}
