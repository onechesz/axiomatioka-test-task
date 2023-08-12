package com.github.onechesz.axiomatikatesttask.dao;

import com.github.onechesz.axiomatikatesttask.entities.ClientEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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

    public List<ClientEntity> search(String lastname, String firstname, String surname, String passport, String phoneNumber) {
        String hql = "FROM ClientEntity WHERE 1 = 1";

        if (lastname != null)
            hql += " AND lastname = :lastname";

        if (firstname != null)
            hql += " AND firstname = :firstname";

        if (surname != null)
            hql += " AND surname = :surname";

        if (passport != null)
            hql += " AND passport = :passport";

        if (phoneNumber != null)
            hql += " AND phoneNumber = :phoneNumber";

        Query<ClientEntity> query = sessionFactory.getCurrentSession().createQuery(hql, ClientEntity.class);

        if (lastname != null)
            query.setParameter("lastname", lastname);

        if (firstname != null)
            query.setParameter("firstname", firstname);

        if (surname != null)
            query.setParameter("surname", surname);

        if (passport != null)
            query.setParameter("passport", passport);

        if (phoneNumber != null)
            query.setParameter("phoneNumber", phoneNumber);

        return query.getResultList();
    }
}
