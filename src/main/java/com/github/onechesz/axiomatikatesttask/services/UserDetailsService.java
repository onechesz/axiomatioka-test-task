package com.github.onechesz.axiomatikatesttask.services;

import com.github.onechesz.axiomatikatesttask.dao.UserDAO;
import com.github.onechesz.axiomatikatesttask.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Отвечает за загрузку и обработку данных пользователя из базы данных для Security
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserDAO userDAO;

    public UserDetailsService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userDAO.findByUsername(username);

        if (username == null)
            throw new UsernameNotFoundException("A user with the such name has not been found.");

        return new com.github.onechesz.axiomatikatesttask.security.UserDetails(userEntity);
    }
}
