package com.github.onechesz.axiomatikatesttask.controllers;

import com.github.onechesz.axiomatikatesttask.services.ClientService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String clientsView(@RequestParam(name = "lastname", required = false) String lastname, @RequestParam(name = "firstname", required = false) String firstname, @RequestParam(name = "surname", required = false) String surname, @RequestParam(name = "passport", required = false) String passport, @RequestParam(name = "phoneNumber", required = false) String phoneNumber, @NotNull Model model) {
        if (lastname == null && firstname == null && surname == null && passport == null && phoneNumber == null)
            model.addAttribute("clients", clientService.findAll());
        else
            model.addAttribute("clients", clientService.search(lastname, firstname, surname, passport, phoneNumber));

        return "user/clients";
    }

    @GetMapping(path = "/applications")
    public String applicationsView(@NotNull Model model) {
        model.addAttribute("applications", clientService.findAllApplications());

        return "user/applications";
    }

    @GetMapping(path = "/credit-agreements")
    public String creditAgreementsView(@NotNull Model model) {
        model.addAttribute("creditAgreements", clientService.findAllCreditAgreements());

        return "user/credit-agreements";
    }
}
