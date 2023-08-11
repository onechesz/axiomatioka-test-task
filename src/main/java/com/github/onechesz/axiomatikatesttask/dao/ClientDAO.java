package com.github.onechesz.axiomatikatesttask.dao;

import com.github.onechesz.axiomatikatesttask.entities.ClientEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ClientDAO {
    private final SessionFactory sessionFactory;

    public ClientDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(ClientEntity clientEntity) {
        Session session = sessionFactory.getCurrentSession();

        session.persist(clientEntity);
    }
}
