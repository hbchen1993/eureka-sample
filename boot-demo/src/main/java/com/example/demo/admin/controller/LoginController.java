package com.example.demo.admin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.admin.entity.User;

@Controller
public class LoginController {

    /**
     * Description: <br>
     * 登录
     * 
     * @author xxx<br>
     * @param user
     * @return <br>
     */
    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    @ResponseBody
    public String login(User user) {

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "已经登录";
        }
        else {
            try {
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(),
                    user.getPassword());
                subject.login(usernamePasswordToken);
                return "登录成功";
            }
            catch (UnknownAccountException e) {
                e.printStackTrace();
                return "UnknownAccountException -- > 账号不存在：";
            }
            catch (IncorrectCredentialsException e) {
                return "IncorrectCredentialsException -- > 密码不正确：";
            }
            catch (AuthenticationException e) {
                e.printStackTrace();
                return e.getCause().getMessage();
            }
        }
    }

    /**
     * Description: <br>
     * 退出
     * 
     * @author xxx<br>
     * @param user
     * @return <br>
     */
    @RequestMapping(value = "doLogout", method = RequestMethod.GET)
    @ResponseBody
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return "退出登录成功";
    }

}
