package com.example.demo.controller;

import com.example.demo.controller.Base.BaseController;
import com.example.demo.dao.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController extends BaseController {
    @RequestMapping(value = "/")
    public String index() {
        return "redirect:login.htm";
    }

    @RequestMapping(value = "/login.htm")
    public String loginPage() {
        return "login";
    }


    /**
     * 主页
     *
     * @return
     */
    @RequestMapping(value = "/main/home.htm")
    public String home(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute(DR_USER);
        model.addAttribute("user", user);
        return "home";
    }

    /**
     * 重定向到登录页面
     *
     * @return
     */
    @RequestMapping(value = "/redirectPost.htm")
    public String redirectPost() {
        return "redirectPost";
    }
}
