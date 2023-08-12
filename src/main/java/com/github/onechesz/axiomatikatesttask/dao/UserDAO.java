package com.github.onechesz.axiomatikatesttask.dao;

import com.github.onechesz.axiomatikatesttask.entities.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Связывается с БД для обработки информации, связанной с сущностью "user"
 */
@Component
@Transactional
public class UserDAO {
    private final SessionFactory sessionFactory;

    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Ищет пользователя в БД по имени и при наличии возвращает его, а при обратном результате выбрасывает исключение
     *
     * @param name
     * @return
     */
    @Transactional(readOnly = true)
    public UserEntity findByUsername(String name) {
        Session session = sessionFactory.getCurrentSession();

        List<UserEntity> userEntityList = session.createQuery("SELECT user FROM UserEntity user WHERE user.name = :name", UserEntity.class).setParameter("name", name).getResultList();

        if (userEntityList.isEmpty())
            return null;

        return userEntityList.get(0);
    }
}
