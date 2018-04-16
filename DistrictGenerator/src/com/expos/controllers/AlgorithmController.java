package com.expos.controllers;


import com.expos.models.StatesEntity;
import com.expos.services.AlgorithmService;
import com.expos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AlgorithmController {


    ModelAndView mv;

    private AlgorithmService algorithmService;

    private StatesEntity currentState;

    @Autowired
    public void setUserService(AlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
    }

//    @RequestMapping(value = "/algorithm")
//    @ResponseBody
//    public ModelAndView getStateName() {
//
//        mv = new ModelAndView("index");
//
//        List<StatesEntity> list = algorithmService.test(2010);
//        for (StatesEntity statesEntity : list) {
//
//        }
//
//
//    }


}
