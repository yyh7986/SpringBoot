package com.himedia.g11.controller;

import com.himedia.g11.dao.IBbsDao;
import com.himedia.g11.dto.BbsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BbsController {

    @Autowired
    IBbsDao bdao;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        List<BbsDto> list = bdao.select();
        mav.addObject("list", list);
        mav.setViewName("bbslist");
        return mav;
    }
}
