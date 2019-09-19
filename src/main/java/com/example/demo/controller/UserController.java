package com.example.demo.controller;

import com.example.demo.controller.Base.BaseController;
import com.example.demo.dao.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = {"/user/"})
public class UserController extends BaseController {


    @Autowired
    private UserService dailyUserService;


    /**
     * 用户登录
     * @param username
     * @param password
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/loginSubmit.htm")
    public String doLogin(String username, String password, HttpServletRequest request, Model model) {
        String md5Passwd = MD5Util.md5Password(password);
        try {
            User user = dailyUserService.queryUser(username, md5Passwd);
            if (user == null) {
                throw new Exception("用户名或密码错误!");
            }
            HttpSession session = request.getSession();
            session.setAttribute(DR_USER, user);
        } catch (Exception e) {
            model.addAttribute(ERROR_MSG, e.getMessage());
            return "login";
        }
        return "redirect:/main/home.htm";
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout.htm")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(DR_USER);
        return "redirect:/login.htm";
    }

    @RequestMapping(value = "/startChangePassword.htm")
    public String startChange() {
        return "user/changePassword";
    }

    /**
     * 修改密码
     * @param request
     * @param password
     * @param newPassword
     * @param model
     * @return
     */
//    @RequestMapping(value = "/changePassword.htm")
//    public String change(HttpServletRequest request, @ModelAttribute("password") String password,
//                         @ModelAttribute("newPassword") String newPassword, Model model) {
//        User user = (User) request.getSession().getAttribute(DR_USER);
//        User dailyUser = null;
//        try {
//            dailyUser = dailyUserService.queryUser(user.getUsername(), MD5Util.md5Password(password));
//            if(dailyUser == null) {
//                throw new Exception("密码错误");
//            }
//            dailyUserService.changePassword(MD5Util.md5Password(newPassword), user.getUid());
//        } catch (Exception e) {
//            model.addAttribute("er_msg", e.getMessage());
//            return "user/changePassword";
//        }
//        model.addAttribute("success_msg", "修改成功");
//        return "user/changePassword";
//    }

}
