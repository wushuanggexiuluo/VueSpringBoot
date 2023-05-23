package com.scm.myblog.controller;

import com.alibaba.fastjson.JSON;
import com.xkcoding.justauth.AuthRequestFactory;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/Auth")
@Slf4j
public class UserAuthController {
    @Autowired
    private AuthRequestFactory factory;


    @GetMapping("/login/{type}")
    public void toLogin(@PathVariable String type, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = factory.get(type);
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @GetMapping("/{type}/callback")
    public AuthResponse loginBack(@PathVariable String type, AuthCallback callback) {
        AuthRequest authRequest = factory.get(type);
        log.info(JSON.toJSONString(callback));
        AuthResponse response = authRequest.login(callback);
        log.info(JSON.toJSONString(response));
        return response;
    }
}
