package com.expos.controllers;

import com.expos.models.UsersEntity;
import com.expos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    ModelAndView mv;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView goHome() {
        mv = new ModelAndView("index");
        mv.addObject("message", "work you fucking cunt");
        System.out.println("INDEX CONTROLLER OPERATIONAL");
        return mv;
    }

    @GetMapping(value = "/group")
    public ModelAndView goToGroup() {
        mv = new ModelAndView("group");
        return mv;
    }

    @GetMapping(value = "/contact")
    public ModelAndView goToContact() {
        mv = new ModelAndView("contact");
        return mv;
    }

    @GetMapping(value = "/about")
    public ModelAndView goToAbout() {
        mv = new ModelAndView("about");
        return mv;
    }

    @GetMapping(value = "/signup")
    public ModelAndView goToSignUp() {
        mv = new ModelAndView("register");
        return mv;
    }

    @GetMapping(value = "/profile")
    public ModelAndView goToProfile() {
        mv = new ModelAndView("profile");
        return mv;
    }

    @GetMapping(value = "/ziZdQ9CSTT")
    @ResponseBody
    public ModelAndView goToPanel(HttpServletRequest request) {
        mv = new ModelAndView("admin");
        HttpSession session = request.getSession(true);
        List<UsersEntity> userList = userService.getUserList();
        session.setAttribute("userList", userList);
        return mv;
    }

}
