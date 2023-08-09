package com.github.onechesz.axiomatikatesttask.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "")
public class MainController {
    @GetMapping(path = "")
    public String indexView() {
        return "main/index";
    }
}
