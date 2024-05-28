package com.duyi.seckill.controller;


import cn.hutool.core.util.StrUtil;
import com.duyi.seckill.entity.User;
import com.duyi.seckill.exception.UserException;
import com.duyi.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/seckill")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/index")
    public String index() {
        return "login";
    }


    @RequestMapping(value = "/getUser")
    @ResponseBody
    public User getUser1(int id) {

        return userService.getUser(id);
    }


    // 请求地址 http://localhost:8080/seckill/getUser/1
    @RequestMapping(value = "/getUser/{uid}")
    @ResponseBody
    public User getUser2(@PathVariable("uid") int uid) {
        return userService.getUser(uid);
    }


    // 用户登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String phone, String password, HttpSession session) {

        if (StrUtil.isEmpty(phone) || phone.length() != 11) {
            throw new UserException("手机号不正确");
        }
        if (StrUtil.isEmpty(password)) {
            throw new UserException("密码不正确");
        }

        if (userService.login(phone, password)) {
            // 返回jsp页面
            // 前缀 --> name="prefix" value="/WEB-INF/jsp/"
            // 后缀 --> name="suffix" value=".jsp"
            // 默认页面的位置：/WEB-INF/jsp/seckill_list.jsp

            // 登录成功
            session.setAttribute("user", new User(phone, password));

            return "forward:/seckill/seckillList";
        }

        return "regist";
    }


    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(String phone, String password1, String password2) {


        // 前段是JS验证，不可靠，可以略过
        // 后端验证，验证参数的合法性
        if (StrUtil.isEmpty(phone) || phone.length() != 11) {
            throw new UserException("手机号不正确");
        }
        if (StrUtil.isEmpty(password1) || StrUtil.isEmpty(password2) || !password1.equals(password2)) {
            throw new UserException("密码不一致");
        }

        User user = new User();
        user.setPhone(phone);
        user.setPassword(password1);

        userService.regist(user);

        return "login";
    }
}
