package com.github.onechesz.axiomatikatesttask.services;

import com.github.onechesz.axiomatikatesttask.dao.ClientDAO;
import com.github.onechesz.axiomatikatesttask.dto.ClientCreditAgreementDTO;
import com.github.onechesz.axiomatikatesttask.dto.ClientDTO;
import com.github.onechesz.axiomatikatesttask.dto.ClientStatusDTO;
import com.github.onechesz.axiomatikatesttask.entities.ClientEntity;
import com.github.onechesz.axiomatikatesttask.entities.CreditAgreementEntity;
import com.github.onechesz.axiomatikatesttask.entities.StatusEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Необходим для обработки данных между контроллером и БД
 */
@Service
public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    /**
     * Обрабатывает информацию из контроллера (конвертация DTO в сущность, вынос случайного решения по поводу кредита,
     * установка текущего времени, создание кредитного договора при необходимости и передаёт её в DAO для сохранения
     *
     * @param clientDTO
     * @return
     */
    public ClientEntity processApplication(ClientDTO clientDTO) {
        ClientEntity clientEntity = ClientDTO.convertToClientEntity(clientDTO);
        boolean isApproved = ThreadLocalRandom.current().nextBoolean();
        StatusEntity statusEntity = new StatusEntity(isApproved, LocalDate.now(), clientEntity);

        if (isApproved) {
            CreditAgreementEntity creditAgreementEntity = new CreditAgreementEntity(false, clientEntity);

            statusEntity.setDaysTerm(ThreadLocalRandom.current().nextInt(30, 365));
            clientEntity.setCreditAgreementEntity(creditAgreementEntity);
        }

        clientEntity.setStatusEntity(statusEntity);
        clientDAO.save(clientEntity);

        return clientEntity;
    }

    /**
     * Передаёт информацию из DAO в контроллер, обрабатывая её ("married" заменяется на "в браке", а "not_married" - на
     * "одинок(-а)")
     *
     * @return
     */
    public List<ClientEntity> findAll() {
        return clientDAO.findAll().stream().peek(clientEntity -> {
            switch (clientEntity.getFamilyStatus()) {
                case "married" -> clientEntity.setFamilyStatus("в браке");
                case "not_married" -> clientEntity.setFamilyStatus("одинок(-а)");
            }

        }).toList();
    }

    /**
     * Передаёт информацию из DAO в контроллер, обрабатывая её ("married" заменяется на "в браке", а "not_married" - на
     * "одинок(-а)")
     *
     * @return
     */
    public List<ClientEntity> search(String lastname, String firstname, String surname, String passport, String phoneNumber) {
        return clientDAO.search(lastname, firstname, surname, passport, phoneNumber).stream().peek(clientEntity -> {
            switch (clientEntity.getFamilyStatus()) {
                case "married" -> clientEntity.setFamilyStatus("в браке");
                case "not_married" -> clientEntity.setFamilyStatus("одинок(-а)");
            }

        }).toList();
    }

    /**
     * Передаёт информацию из DAO в контроллер, меняя у каждой записи статус с "true" на "одобрена" и с "false" на "не
     * одобрена"
     *
     * @return
     */
    public List<ClientStatusDTO> findAllApplications() {
        return clientDAO.findAllApplications().stream().peek(clientStatusDTO -> {
            if (clientStatusDTO.isApproved()) clientStatusDTO.setIsApprovedRu("одобрена");
            else clientStatusDTO.setIsApprovedRu("не одобрена");
        }).toList();
    }

    /**
     * Передаёт информацию из DAO в контроллер, меняя у каждой записи статус с "true" на "да" и с "false" на "нет"
     *
     * @return
     */
    public List<ClientCreditAgreementDTO> findAllCreditAgreements() {
        return clientDAO.findAllCreditAgreements().stream().peek(clientCreditAgreementDTO -> {
            if (clientCreditAgreementDTO.isSigned()) clientCreditAgreementDTO.setIsSignedRu("да");
            else clientCreditAgreementDTO.setIsSignedRu("нет");
        }).toList();
    }

    /**
     * Передаёт информацию о клиенте из DAO
     *
     * @param passport
     * @return
     */
    public Optional<ClientEntity> findByPassport(String passport) {
        return clientDAO.findByPassport(passport);
    }
}
