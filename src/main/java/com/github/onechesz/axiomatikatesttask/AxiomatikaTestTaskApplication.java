package com.github.onechesz.axiomatikatesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * Инициальный класс запуска приложения
 */
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class AxiomatikaTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(AxiomatikaTestTaskApplication.class, args);
    }

}
