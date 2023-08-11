package com.github.onechesz.axiomatikatesttask.dao;

import com.github.onechesz.axiomatikatesttask.entities.CreditAgreementEntity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CreditAgreementDAO {
    private final SessionFactory sessionFactory;

    public CreditAgreementDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(CreditAgreementEntity creditAgreementEntity) {
        sessionFactory.getCurrentSession().merge(creditAgreementEntity);
    }
}
