package com.fastcampus.restaurant.controller.pages;

import com.fastcampus.restaurant.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final UserService userService;

    @RequestMapping(path = {"","/main"})
    public ModelAndView main(@RequestParam("query") String query){
        log.info("검색어 : {}", query);
        var searchResultDto = userService.search(query);

        log.info("결과 : {}", searchResultDto);
        ModelAndView modelAndView = new ModelAndView("main");
        modelAndView.addObject("searchResultDto", searchResultDto);
        modelAndView.addObject("searchKeyWord",query);
        return modelAndView;
    }
}
