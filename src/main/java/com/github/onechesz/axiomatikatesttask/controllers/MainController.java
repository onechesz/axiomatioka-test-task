package com.github.onechesz.axiomatikatesttask.controllers;

import com.github.onechesz.axiomatikatesttask.dto.ClientDTO;
import com.github.onechesz.axiomatikatesttask.entities.ClientEntity;
import com.github.onechesz.axiomatikatesttask.services.ClientService;
import com.github.onechesz.axiomatikatesttask.services.CreditAgreementService;
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
    private final CreditAgreementService creditAgreementService;

    public MainController(ClientService clientService, CreditAgreementService creditAgreementService) {
        this.clientService = clientService;
        this.creditAgreementService = creditAgreementService;
    }

    @GetMapping(path = "")
    public String indexView() {
        return "main/index";
    }

    @GetMapping(path = "/application")
    public String applicationView(@NotNull HttpServletRequest httpServletRequest, @NotNull Model model) {
        if (httpServletRequest.getSession().getAttribute("client") == null) {
            model.addAttribute("client", new ClientDTO());

            return "main/application";
        }

        return "redirect:/decision";
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
        ClientEntity clientEntity = (ClientEntity) httpServletRequest.getSession().getAttribute("client");

        if (clientEntity != null) {
            model.addAttribute("client", clientEntity);

            return "main/decision";
        }

        return "redirect:/";
    }

    @PostMapping(path = "/new-client")
    public String newClientProcess(@NotNull HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute("client");

        return "redirect:/application";
    }

    @GetMapping(path = "/agreement")
    public String agreementView(@NotNull HttpServletRequest httpServletRequest, Model model) {
        ClientEntity clientEntity = (ClientEntity) httpServletRequest.getSession().getAttribute("client");

        if (clientEntity != null && clientEntity.getStatusEntity().isApproved()) {
            model.addAttribute("client", clientEntity);

            return "main/agreement";
        }

        return "redirect:/application";
    }

    @PostMapping(path = "/agreement")
    public String agreementProcess(@NotNull HttpServletRequest httpServletRequest) {
        creditAgreementService.save((ClientEntity) httpServletRequest.getSession().getAttribute("client"));

        return "redirect:/agreement";
    }
}
