package com.scm.myblog.controller.blogcontrollers;

import com.scm.myblog.entity.CORE.StatusMes;
import com.scm.myblog.entity.VO.Result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Error2")
@Slf4j
public class Error2Controller {
    @GetMapping("/Auth")
    public Result throwException(HttpServletRequest request) throws Exception {
        //验证是那种失败信息
        String codeString = (String) request.getAttribute("code");
        log.info(codeString);
        if ((codeString).equals(StatusMes.LOGIN_LAPSE.getCode())){
            return new Result(null,StatusMes.LOGIN_LAPSE.getCode(), StatusMes.LOGIN_LAPSE.getMessage());
        }
        return null;
    }

}
