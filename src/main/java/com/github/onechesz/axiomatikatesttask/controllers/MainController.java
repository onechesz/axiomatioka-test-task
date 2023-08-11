package com.github.onechesz.axiomatikatesttask.controllers;

import com.github.onechesz.axiomatikatesttask.dto.ClientDTO;
import com.github.onechesz.axiomatikatesttask.services.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "")
public class MainController {
    private final ClientService clientService;

    public MainController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(path = "")
    public String indexView() {
        return "main/index";
    }

    @GetMapping(path = "/application")
    public String applicationView(@NotNull Model model) {
        model.addAttribute("client", new ClientDTO());

        return "main/application";
    }

    @PostMapping(path = "/application")
    public String applicationProcess(HttpServletRequest httpServletRequest, @ModelAttribute(name = "client") @Valid ClientDTO clientDTO, @NotNull BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "main/application";

        httpServletRequest.getSession().setAttribute("client", clientService.processApplication(clientDTO));

        return "redirect:/decision";
    }

    @GetMapping(path = "/decision")
    public String decisionView(@NotNull HttpServletRequest httpServletRequest, @NotNull Model model) {
        model.addAttribute("client", httpServletRequest.getSession().getAttribute("client"));

        return "main/decision";
    }
}
