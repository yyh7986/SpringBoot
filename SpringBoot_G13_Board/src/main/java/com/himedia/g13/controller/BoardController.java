package com.himedia.g13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/boardList")
    public String boardlist() {

        return "board/boardList";
    }
}
