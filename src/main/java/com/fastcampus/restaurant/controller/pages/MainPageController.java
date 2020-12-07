package com.fastcampus.restaurant.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainPageController {

    @RequestMapping(path = {"","/main"})
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView("main");
        modelAndView.addObject("hello","hi java");
        return modelAndView;
    }
}
