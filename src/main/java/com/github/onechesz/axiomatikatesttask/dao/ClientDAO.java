package com.github.onechesz.axiomatikatesttask.dao;

import com.github.onechesz.axiomatikatesttask.entities.ClientEntity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ClientDAO {
    private final SessionFactory sessionFactory;

    public ClientDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(ClientEntity clientEntity) {
        sessionFactory.getCurrentSession().persist(clientEntity);
    }

    public List<ClientEntity> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM ClientEntity", ClientEntity.class).list();
    }
}
