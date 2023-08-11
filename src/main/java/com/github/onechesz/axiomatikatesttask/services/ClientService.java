package com.github.onechesz.axiomatikatesttask.services;

import com.github.onechesz.axiomatikatesttask.dao.ClientDAO;
import com.github.onechesz.axiomatikatesttask.dto.ClientDTO;
import com.github.onechesz.axiomatikatesttask.entities.ClientEntity;
import com.github.onechesz.axiomatikatesttask.entities.StatusEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public ClientEntity processApplication(ClientDTO clientDTO) {
        ClientEntity clientEntity = ClientDTO.convertToClientEntity(clientDTO);
        boolean isApproved = ThreadLocalRandom.current().nextBoolean();
        StatusEntity statusEntity = new StatusEntity(isApproved, LocalDate.now(), clientEntity);

        if (isApproved)
            statusEntity.setDaysTerm(ThreadLocalRandom.current().nextInt(30, 365));

        clientEntity.setStatusEntity(statusEntity);
        clientDAO.save(clientEntity);

        return clientEntity;
    }
}
