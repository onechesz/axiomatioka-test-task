package com.github.onechesz.axiomatikatesttask.services;

import com.github.onechesz.axiomatikatesttask.dao.CreditAgreementDAO;
import com.github.onechesz.axiomatikatesttask.entities.ClientEntity;
import com.github.onechesz.axiomatikatesttask.entities.CreditAgreementEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Служит для обработки данных между контроллером и DAO
 */
@Service
public class CreditAgreementService {
    private final CreditAgreementDAO creditAgreementDAO;

    public CreditAgreementService(CreditAgreementDAO creditAgreementDAO) {
        this.creditAgreementDAO = creditAgreementDAO;
    }

    /**
     * Задаёт статус "true" для кледитного договора сущности клиента, устанавливает текущие дату и время внутри и
     * предаёт объект клиента на сохранение в DAO
     *
     * @param clientEntity
     */
    public void save(@NotNull ClientEntity clientEntity) {
        CreditAgreementEntity creditAgreementEntity = clientEntity.getCreditAgreementEntity();

        creditAgreementEntity.setSigned(true);
        creditAgreementEntity.setSignDate(LocalDateTime.now());
        creditAgreementDAO.save(creditAgreementEntity);
    }
}
