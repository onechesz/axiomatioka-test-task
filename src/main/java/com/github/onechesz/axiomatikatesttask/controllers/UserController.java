package com.github.onechesz.axiomatikatesttask.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/manage")
public class UserController {
    @GetMapping(path = "/login")
    public String loginView() {
        return "user/login";
    }

    @GetMapping(path = "")
    public String manageView() {
        return "user/manage";
    }
}
