package com.github.onechesz.axiomatikatesttask.services;

import com.github.onechesz.axiomatikatesttask.dao.ClientDAO;
import com.github.onechesz.axiomatikatesttask.dto.ClientDTO;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public void processApplication(ClientDTO clientDTO) {
        clientDAO.save(ClientDTO.convertToClientEntity(clientDTO));
    }
}
