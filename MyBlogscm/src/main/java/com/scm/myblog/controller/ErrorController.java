package com.scm.myblog.controller;

import com.scm.myblog.entity.StatusMes;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.exception.DefinitionException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/Error")
public class ErrorController {
    @GetMapping("/Auth")
    public Result throwException(HttpServletRequest request) throws Exception {
        //验证是那种失败信息
        Integer code = (Integer)request.getAttribute("code");
        assert code != null;
        if (code.equals(StatusMes.LOGIN_LAPSE.getCode())){
            throw new DefinitionException(StatusMes.LOGIN_LAPSE.getCode(), StatusMes.LOGIN_LAPSE.getMes());
        }
        else {
            return null;
        }
    }
}
