package com.himedia.g14.controller;

import com.himedia.g14.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
public class ProductController {

    @Autowired
    ProductService ps;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();

        HashMap<String, Object> result = ps.getBestNewList();
        mav.addObject("newList", result.get("newList"));
        mav.addObject("bestList", result.get("bestList"));

        mav.setViewName("main");
        return mav;
    }
}
