package com.csjbot.robot.biz.ums.admin;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csjbot.robot.base.exception.DisableAccountException;

@Controller
public class IndexController {
    @RequestMapping(value = "login")
    public ModelAndView indexGet(HttpServletRequest request, ModelAndView mav) {
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "账号不存在,请重新输入.";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "密码错误";
        } else if (DisableAccountException.class.getName().equals(exceptionClassName)) {
            error = "账号已经被停用，请联系系统管理员";
        } else if ("jcaptcha.error".equals(exceptionClassName)) {
            error = "验证码错误";
        } else if ("jcaptcha.expired".equals(exceptionClassName)) {
            error = "验证码失效，请重新获取";
        } else if (exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        mav.getModel().put("error", error);
        mav.setViewName("index/login");
        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("main");
    }

}
