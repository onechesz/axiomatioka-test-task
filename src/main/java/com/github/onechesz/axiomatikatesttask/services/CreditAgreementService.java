package com.github.onechesz.axiomatikatesttask.services;

import com.github.onechesz.axiomatikatesttask.dao.CreditAgreementDAO;
import com.github.onechesz.axiomatikatesttask.entities.ClientEntity;
import com.github.onechesz.axiomatikatesttask.entities.CreditAgreementEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreditAgreementService {
    private final CreditAgreementDAO creditAgreementDAO;

    public CreditAgreementService(CreditAgreementDAO creditAgreementDAO) {
        this.creditAgreementDAO = creditAgreementDAO;
    }

    public void save(@NotNull ClientEntity clientEntity) {
        CreditAgreementEntity creditAgreementEntity = clientEntity.getCreditAgreementEntity();

        creditAgreementEntity.setSigned(true);
        creditAgreementEntity.setSignDate(LocalDateTime.now());
        creditAgreementDAO.save(creditAgreementEntity);
    }
}
