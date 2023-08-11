package com.github.onechesz.axiomatikatesttask.dao;

import com.github.onechesz.axiomatikatesttask.entities.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UserDAO {
    private final SessionFactory sessionFactory;

    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public UserEntity findByUsername(String name) {
        Session session = sessionFactory.getCurrentSession();

        List<UserEntity> userEntityList = session.createQuery("SELECT user FROM UserEntity user WHERE user.name = :name", UserEntity.class).setParameter("name", name).getResultList();

        if (userEntityList.isEmpty())
            return null;

        return userEntityList.get(0);
    }
}
