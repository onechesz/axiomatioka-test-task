package com.github.onechesz.axiomatikatesttask.controllers;

import com.github.onechesz.axiomatikatesttask.dto.ClientDTO;
import com.github.onechesz.axiomatikatesttask.entities.ClientEntity;
import com.github.onechesz.axiomatikatesttask.services.ClientService;
import com.github.onechesz.axiomatikatesttask.services.CreditAgreementService;
import com.github.onechesz.axiomatikatesttask.validators.ClientValidator;
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

/**
 * Отвечает за взаимодействие с клиентом
 */
@Controller
@RequestMapping(path = "/client")
public class ClientController {
    private final ClientService clientService;
    private final CreditAgreementService creditAgreementService;
    private final ClientValidator clientValidator;

    public ClientController(ClientService clientService, CreditAgreementService creditAgreementService, ClientValidator clientValidator) {
        this.clientService = clientService;
        this.creditAgreementService = creditAgreementService;
        this.clientValidator = clientValidator;
    }

    /**
     * Отвечает за отображение страницы с заполнением форм заявки на кредит (если пользователь пытается попасть сюда,
     * уже отправвив заявку, его переадресует на страницу с решением по отправленной заявке
     *
     * @param httpServletRequest
     * @param model
     * @return
     */
    @GetMapping(path = "/application")
    public String applicationView(@NotNull HttpServletRequest httpServletRequest, @NotNull Model model) {
        if (httpServletRequest.getSession().getAttribute("client") == null) {
            model.addAttribute("client", new ClientDTO());

            return "client/application";
        }

        return "redirect:/client/decision";
    }

    /**
     * Ловит форму заявки, проверяет поля на наличие синтаксических ошибок и направляет данные на обработку в сервис,
     * после чего переадресовывает пользователся на страницу с решением по его заявке
     *
     * @param httpServletRequest
     * @param clientDTO
     * @param bindingResult
     * @return
     */
    @PostMapping(path = "/application")
    public String applicationProcess(HttpServletRequest httpServletRequest, @ModelAttribute(name = "client") @Valid ClientDTO clientDTO, @NotNull BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "client/application";

        clientValidator.validate(clientDTO, bindingResult);

        if (bindingResult.hasErrors()) return "client/application";

        httpServletRequest.getSession().setAttribute("client", clientService.processApplication(clientDTO));

        return "redirect:/client/decision";
    }

    /**
     * Отображает страницу с решением по пользовательской заявке; если попытаться попасть на такую страницу, не заполнив
     * заявку, произойдёт переадресация пользователя на страницу заполнения заявки
     *
     * @param httpServletRequest
     * @param model
     * @return
     */
    @GetMapping(path = "/decision")
    public String decisionView(@NotNull HttpServletRequest httpServletRequest, @NotNull Model model) {
        ClientEntity clientEntity = (ClientEntity) httpServletRequest.getSession().getAttribute("client");

        if (clientEntity != null) {
            model.addAttribute("client", clientEntity);

            return "client/decision";
        }

        return "redirect:/client/application";
    }

    /**
     * Ловит тех, кому не повезло получить одобрение на кредит, после чего они решили попытать удачу ещё раз, заполнив
     * новую заявку; очищает сессию от "клиента"
     *
     * @param httpServletRequest
     * @return
     */
    @PostMapping(path = "/new-client")
    public String newClientProcess(@NotNull HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute("client");

        return "redirect:/client/application";
    }

    /**
     * Отображает страницу с возможностю подписания кредитного договора
     *
     * @param httpServletRequest
     * @param model
     * @return
     */
    @GetMapping(path = "/agreement")
    public String agreementView(@NotNull HttpServletRequest httpServletRequest, Model model) {
        ClientEntity clientEntity = (ClientEntity) httpServletRequest.getSession().getAttribute("client");

        if (clientEntity != null && clientEntity.getStatusEntity().isApproved()) {
            model.addAttribute("client", clientEntity);

            return "client/agreement";
        }

        return "redirect:/client/application";
    }

    /**
     * Ловит "подпись" кредитного договора, передавая её в сервис
     *
     * @param httpServletRequest
     * @return
     */
    @PostMapping(path = "/agreement")
    public String agreementProcess(@NotNull HttpServletRequest httpServletRequest) {
        creditAgreementService.save((ClientEntity) httpServletRequest.getSession().getAttribute("client"));

        return "redirect:/client/agreement";
    }
}
