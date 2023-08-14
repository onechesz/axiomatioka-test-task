package com.github.onechesz.axiomatikatesttask.dao;

import com.github.onechesz.axiomatikatesttask.dto.ClientCreditAgreementDTO;
import com.github.onechesz.axiomatikatesttask.dto.ClientStatusDTO;
import com.github.onechesz.axiomatikatesttask.entities.ClientEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Обеспечивает доступ к данным из БД
 */
@Component
@Transactional
public class ClientDAO {
    private final SessionFactory sessionFactory;

    public ClientDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Сохраняет нового клиента
     *
     * @param clientEntity
     */
    public void save(ClientEntity clientEntity) {
        sessionFactory.getCurrentSession().persist(clientEntity);
    }

    /**
     * Возвращает все записи из таблицы "client"
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<ClientEntity> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM ClientEntity", ClientEntity.class).list();
    }

    /**
     * Обеспечивает поиск по ключевым словам; если одно из них будет null - оно в поиске не учитывается
     *
     * @param lastname
     * @param firstname
     * @param surname
     * @param passport
     * @param phoneNumber
     * @return
     */
    @Transactional(readOnly = true)
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

    /**
     * Возвращает все заявки вместе с минимальной информацией о клиенте, который её создал
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<ClientStatusDTO> findAllApplications() {
        return sessionFactory.getCurrentSession().createQuery("SELECT NEW com.github.onechesz.axiomatikatesttask.dto.ClientStatusDTO(c.id, c.lastname, c.firstname, c.surname, c.sum, s.isApproved, s.date, s.daysTerm) FROM ClientEntity c LEFT JOIN c.statusEntity s", ClientStatusDTO.class).getResultList();
    }

    /**
     * Возвращает все кредитные договоры с минимальной информацией о его владельце
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<ClientCreditAgreementDTO> findAllCreditAgreements() {
        return sessionFactory.getCurrentSession().createQuery("SELECT NEW com.github.onechesz.axiomatikatesttask.dto.ClientCreditAgreementDTO(c.id, c.lastname, c.firstname, c.surname, cr.isSigned, cr.signDate) FROM ClientEntity c INNER JOIN c.creditAgreementEntity cr", ClientCreditAgreementDTO.class).getResultList();
    }

    /**
     * Возвращает клиента, найденного по паспорту или ничего
     *
     * @param passport
     * @return
     */
    @Transactional(readOnly = true)
    public Optional<ClientEntity> findByPassport(String passport) {
        return Optional.of(sessionFactory.getCurrentSession().createQuery("FROM ClientEntity c WHERE c.passport = :passport", ClientEntity.class).setParameter("passport", passport).getSingleResult());
    }
}
