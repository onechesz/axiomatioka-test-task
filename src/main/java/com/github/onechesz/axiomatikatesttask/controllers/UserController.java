package com.github.onechesz.axiomatikatesttask.controllers;

import com.github.onechesz.axiomatikatesttask.services.ClientService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/manage")
public class UserController {
    private final ClientService clientService;

    public UserController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(path = "/login")
    public String loginView() {
        return "user/login";
    }

    @GetMapping(path = "")
    public String manageView() {
        return "user/manage";
    }

    @GetMapping(path = "/clients")
    public String clientsView(@NotNull Model model) {
        model.addAttribute("clients", clientService.findAll());

        return "user/clients";
    }
}
