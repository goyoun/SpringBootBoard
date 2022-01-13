package com.icia.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // 인덱스 호출
    @GetMapping("/")
    public String index() {

        return "index";
    }
}
