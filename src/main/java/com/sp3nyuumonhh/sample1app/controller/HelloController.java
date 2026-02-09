package com.sp3nyuumonhh.sample1app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {
    @RequestMapping("/{temp}")
    public String index(@PathVariable String temp) {
        switch (temp) {
            case "index":
                return "index";
            default:
                return "other";
        }
    }
}
