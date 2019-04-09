package com.zyy.controller;

import com.zyy.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yangyang.zhang
 * @date 2018/11/17 15:46
 */
@Controller
public class IndexController {

    @PostMapping("/login")
    public String login(User user) {
        Subject subject = SecurityUtils.getSubject();
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return "login";
        }
        try {
            subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
        } catch (AuthenticationException e) {
            return "403";
        }
        return "index";
    }

    @GetMapping
    public String index() {
        return "login";
    }
}
