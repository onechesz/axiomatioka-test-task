package com.github.onechesz.axiomatikatesttask.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Необходим для отображения страниц корневых запросов
 */
@Controller
@RequestMapping(path = "")
public class MainController {
    /**
     * Отображает главную страницу
     *
     * @return
     */
    @GetMapping(path = "")
    public String indexView() {
        return "index";
    }
}
