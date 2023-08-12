package com.github.onechesz.axiomatikatesttask.controllers;

import com.github.onechesz.axiomatikatesttask.services.ClientService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Необходим для отображения страниц запросов, связанных с менеджером
 */
@Controller
@RequestMapping(path = "/manage")
public class UserController {
    private final ClientService clientService;

    public UserController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Отображает страницу входа в приложение, (вход реализован при помощи Security)
     *
     * @return
     */
    @GetMapping(path = "/login")
    public String loginView() {
        return "user/login";
    }

    /**
     * Отображает страницу личного кабинета менеджера
     *
     * @return
     */
    @GetMapping(path = "")
    public String manageView() {
        return "user/manage";
    }

    /**
     * Отображает страницу с клиентами; принимает 5 параметров для поиска, каждый из которых может быть null (если поле
     * пустое - смотреть <script></script> в templates/user/clients.html), после чего передаёт их в сервис и отображает
     * результат, возвращённых им же (найденные записи в БД в соответсвии с аргументами поиска)
     *
     * @param lastname
     * @param firstname
     * @param surname
     * @param passport
     * @param phoneNumber
     * @param model
     * @return
     */
    @GetMapping(path = "/clients")
    public String clientsView(@RequestParam(name = "lastname", required = false) String lastname, @RequestParam(name = "firstname", required = false) String firstname, @RequestParam(name = "surname", required = false) String surname, @RequestParam(name = "passport", required = false) String passport, @RequestParam(name = "phoneNumber", required = false) String phoneNumber, @NotNull Model model) {
        if (lastname == null && firstname == null && surname == null && passport == null && phoneNumber == null)
            model.addAttribute("clients", clientService.findAll());
        else
            model.addAttribute("clients", clientService.search(lastname, firstname, surname, passport, phoneNumber));

        return "user/clients";
    }

    /**
     * Отображает страницу со всеми заявками со статусами одобрений
     *
     * @param model
     * @return
     */
    @GetMapping(path = "/applications")
    public String applicationsView(@NotNull Model model) {
        model.addAttribute("applications", clientService.findAllApplications());

        return "user/applications";
    }

    /**
     * Отображает страницу со всеми кредитными договорами, показывает, подписаны они или нет
     *
     * @param model
     * @return
     */
    @GetMapping(path = "/credit-agreements")
    public String creditAgreementsView(@NotNull Model model) {
        model.addAttribute("creditAgreements", clientService.findAllCreditAgreements());

        return "user/credit-agreements";
    }
}
