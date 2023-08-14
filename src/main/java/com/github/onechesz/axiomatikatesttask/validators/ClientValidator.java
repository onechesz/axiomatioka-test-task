package com.github.onechesz.axiomatikatesttask.validators;

import com.github.onechesz.axiomatikatesttask.dto.ClientDTO;
import com.github.onechesz.axiomatikatesttask.services.ClientService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Отвечает за валидацию клиента, когда необходимо связываться с базой данных
 */
@Component
public class ClientValidator implements Validator {
    private final ClientService clientService;

    public ClientValidator(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * По контракту этот класс может валидировать только инстансы класса ClientDTO - эта функция проверяет, является ли
     * передаваемый параметр таковым
     *
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return ClientDTO.class.equals(clazz);
    }

    /**
     * Связывается с базой данных и валидирует данные, переданные пользователем (в данном случае проверяет, была ли уже
     * оформлена заявка на кредит от клиента с таким паспортом или нет
     *
     * @param target
     * @param errors
     */
    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        if (clientService.findByPassport(((ClientDTO) target).getPassport()).isPresent())
            errors.rejectValue("passport", "", "клиент с таким паспортом уже подавал заявку на кредит");
    }
}
