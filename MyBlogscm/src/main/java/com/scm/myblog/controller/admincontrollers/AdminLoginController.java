package com.scm.myblog.controller.admincontrollers;

import com.scm.myblog.entity.CORE.Code;
import com.scm.myblog.entity.DTO.LoginDto;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.impl.LoginServiceImpl;
import com.scm.myblog.utils.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Objects;

@Api(tags = "管理登陆注册控制器")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Admin")
@Slf4j
public class AdminLoginController {
    @Autowired
    public LoginServiceImpl loginService;

    @ApiOperation("登陆注册")
    @PostMapping("/Login")
    public Result startLogin(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestBody @Validated LoginDto loginDto,
                             BindingResult b
    ) {
        //参数校验通过后才进行操作
        if (!b.hasErrors()) {
            Result result = loginService.Login(loginDto);
            String code = result.getCode();
            //密码正确设置session
            if (Objects.equals(code, Code.PASSWORD_OK) || Objects.equals(code, Code.PASSWORD_OK_ADMIN) || Objects.equals(code, Code.REGISTER_OK)) {
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
                    log.info("有人登陆了多次次！疑似攻击，ip为" + request.getRemoteAddr());
                }
                log.info("登陆用户的sessionID为：" + sessionId);
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
        return new Result(null, "1", "1");
    }

    @ApiOperation("退出登录")
    @PostMapping("/CancelLogin")
    public Result CancelLogin(HttpServletRequest request, HttpServletResponse response) {
        WebUtils.removeUserSession(request);
        log.info("用户退出成功！");
        return new Result(null, Code.Cancel_OK, "用户退出成功");
    }
}
