package com.github.onechesz.axiomatikatesttask.services;

import com.github.onechesz.axiomatikatesttask.dao.ClientDAO;
import com.github.onechesz.axiomatikatesttask.dto.ClientDTO;
import com.github.onechesz.axiomatikatesttask.entities.ClientEntity;
import com.github.onechesz.axiomatikatesttask.entities.CreditAgreementEntity;
import com.github.onechesz.axiomatikatesttask.entities.StatusEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public ClientEntity processApplication(ClientDTO clientDTO) {
        ClientEntity clientEntity = ClientDTO.convertToClientEntity(clientDTO);
        boolean isApproved = true;
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

    public List<ClientEntity> findAll() {
        return clientDAO.findAll().stream().peek(clientEntity -> {
            switch (clientEntity.getFamilyStatus()) {
                case "married" -> clientEntity.setFamilyStatus("в браке");
                case "not_married" -> clientEntity.setFamilyStatus("одинок(-а)");
            }

        }).toList();
    }

    public List<ClientEntity> search(String lastname, String firstname, String surname, String passport, String phoneNumber) {
        return clientDAO.search(lastname, firstname, surname, passport, phoneNumber).stream().peek(clientEntity -> {
            switch (clientEntity.getFamilyStatus()) {
                case "married" -> clientEntity.setFamilyStatus("в браке");
                case "not_married" -> clientEntity.setFamilyStatus("одинок(-а)");
            }

        }).toList();
    }
}
