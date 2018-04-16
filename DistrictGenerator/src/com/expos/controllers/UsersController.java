package com.expos.controllers;

import com.expos.models.UsersEntity;
import com.expos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@SessionAttributes("currentUser")
@RequestMapping(value = "/user")
public class UsersController {
    ModelAndView mv;
    private UserService userService;
    private UsersEntity currentUser;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public String registerUser(@RequestParam Map<String, String> allRequestParam) {
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
    public ModelAndView loginUser(@RequestParam Map<String, String> allRequestParam, HttpServletRequest request) {
        currentUser = userService.login(allRequestParam.get("username"), allRequestParam.get("password"));
        HttpSession session = request.getSession(true);
        mv = new ModelAndView("membernav");
        if (!(currentUser.equals(null))) {
            System.out.println("Logging in currentUser = " + currentUser.getUsername());
            session.setAttribute("loggedIn", 1);
            session.setAttribute("currentUser", currentUser);
        } else {
            session.setAttribute("loggedIn", 0);
        }
        return mv;
    }

    @GetMapping(value = "/logout")
    @ResponseBody
    public ModelAndView logoutUser(HttpServletRequest request, SessionStatus status) {
        HttpSession session = request.getSession(true);
        mv = new ModelAndView("nav");
        session.invalidate();
        status.setComplete();
        return mv;
    }

    @GetMapping(value = "/changeNickname")
    @ResponseBody
    public void changeNickname(@RequestParam Map<String, String> allRequestParams, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        currentUser = (UsersEntity) session.getAttribute("currentUser");
        currentUser.setNickname(allRequestParams.get("nickname"));
        userService.update(currentUser);
    }

    @GetMapping(value = "/changePassword")
    @ResponseBody
    public void changePassword(@RequestParam Map<String, String> allRequestParams, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        currentUser = (UsersEntity) session.getAttribute("currentUser");
        currentUser.setPassword(allRequestParams.get("password"));
        userService.update(currentUser);
    }

    @GetMapping(value = "/removeuser")
    @ResponseBody
    public void removeUser(@RequestParam Map<String, String> allRequestParams) {
        currentUser = userService.getUser(allRequestParams.get("username"));
        userService.delete(currentUser);
    }

    @GetMapping(value = "/addprivilege")
    @ResponseBody
    public void addPrivilege(@RequestParam Map<String, String> allRequestParams) {
        currentUser = userService.getUser(allRequestParams.get("username"));
        System.out.println(currentUser.getUsername());
        currentUser.setAdmin(1);
        userService.update(currentUser);
    }

    @GetMapping(value = "/removeprivilege")
    @ResponseBody
    public void removePrivilege(@RequestParam Map<String, String> allRequestParams) {
        currentUser = userService.getUser(allRequestParams.get("username"));
        System.out.println(currentUser.getAdmin());
        currentUser.setAdmin(0);
        userService.update(currentUser);
    }

}
