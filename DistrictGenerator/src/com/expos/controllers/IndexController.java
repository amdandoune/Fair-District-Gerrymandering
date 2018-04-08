package com.expos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    ModelAndView mv;

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


}
