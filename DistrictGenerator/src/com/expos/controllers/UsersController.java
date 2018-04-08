package com.expos.controllers;

import com.expos.models.UsersEntity;
import com.expos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@SessionAttributes("currentUser")
public class UsersController {

    ModelAndView mv;
    private UserService userService;

    //    @Autowired
    private UsersEntity currentUser;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public String registerUser(@RequestParam Map<String, String> allRequestParam) {
        mv = new ModelAndView("index");

        if (userService.exists(allRequestParam.get("username"))) {
            return "error";
        } else {
            try {
                UsersEntity user = new UsersEntity();
                user.setNickname(allRequestParam.get("nickname"));
                user.setPassword(allRequestParam.get("password"));
                user.setUsername(allRequestParam.get("username"));
                user.setAdmin(0);
                userService.save(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "ok";
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public ModelAndView loginUser(@RequestParam Map<String, String> allRequestParam, HttpSession session) {
        currentUser = userService.login(allRequestParam.get("username"), allRequestParam.get("password"));
        mv = new ModelAndView("membernav");
        if (!(currentUser.equals(null))) {
            System.out.println("Hello");
            session.setAttribute("loggedIn", 1);
            session.setAttribute("userNickname", currentUser.getNickname());
        } else {
            System.out.println("World");
            session.setAttribute("loggedIn", 0);
        }
        return mv;
    }

    @GetMapping(value = "/logout")
    @ResponseBody
    public ModelAndView logoutUser(HttpSession session) {
        mv = new ModelAndView("nav");
        session.invalidate();
        return mv;
    }

}
