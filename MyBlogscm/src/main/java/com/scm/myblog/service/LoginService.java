package com.scm.myblog.service;

import com.scm.myblog.entity.DTO.LoginDto;
import com.scm.myblog.entity.VO.Result;

public interface LoginService {
    /**
     * 登录/注册一体
     *
     * @param loginDto 登录dto
     * @return {@link Result}
     */
     Result Login(LoginDto loginDto);

    /**
     * 是否存在用户
     *
     * @param loginDto 登录dto
     * @return {@link Result}
     */
     Result isExistUser(LoginDto loginDto);

    Result Register(LoginDto loginDto);

    Result logout();
}
