package com.scm.myblog.controller;

import com.scm.myblog.entity.Code;
import com.scm.myblog.entity.DTO.LoginDto;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.impl.LoginServiceImpl;
import com.scm.myblog.utils.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashSet;

    @Api(tags = "管理登陆注册控制器")
    @CrossOrigin(origins = "*")
    @RestController
    @RequestMapping("/Admin")
    public class AdminLoginController {
        @Autowired
        public LoginServiceImpl loginService;
        @ApiOperation("登陆注册")
        @PostMapping("/Login")
        public Result startLogin(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestBody @Valid LoginDto loginDto,
                                 BindingResult b
        ) {
            //参数校验通过后才进行操作
            if(!b.hasErrors()){
                Result result = loginService.Login(loginDto);
                int code = result.getCode();
                //密码正确设置session
                if (code == Code.PASSWORD_OK || code == Code.PASSWORD_OK_ADMIN || code == Code.REGISTER_OK) {
                    //获取session
                    HttpSession session = request.getSession();
                    String sessionId = session.getId();
                    //将新登陆人的sessionid，存入session中
                    if (session.getAttribute("user") == null) {
                        session.setAttribute("user", sessionId);
                        //将新的sessionid放入域中
                        HashSet<String> ses = (HashSet<String>) request.getServletContext().getAttribute("set");
                        ses.add(sessionId);
                    }
                    else {
                        System.out.println("有人登陆了多次次！疑似攻击，ip为"+request.getRemoteAddr());
                    }
                    System.out.println("登陆用户的sessionID为：" + sessionId);
                    //将sessionid存入cookie并返回
                    response.addCookie(WebUtils.setCookieIsSessionId(sessionId));
                }
                return result;
            }
            else {
                throw new RuntimeException();
            }
        }
        @ApiOperation("存在用户")
        @PostMapping("/isExistUser")
        public Result isExistUser(@RequestBody @ApiParam("登陆dto对象") LoginDto loginDto) {
            return loginService.isExistUser(loginDto);
        }
        @ApiOperation("获取数据")
        @GetMapping("/getOnInternatCount")
        public Result getCount(HttpServletRequest request) {
            WebUtils.getOnInternatCount(request);
            return new Result(null, 1, "1");
        }
        @ApiOperation("退出登录")
        @PostMapping("/CancelLogin")
        public Result CancelLogin(HttpServletRequest request, HttpServletResponse response) {
            WebUtils.removeUserSession(request);
            System.out.println("用户退出成功！");
            return new Result(null, Code.Cancel_OK, "用户退出成功");
        }
    }
