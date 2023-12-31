package com.github.onechesz.axiomatikatesttask.configs;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Базовая конфигурация Spring приложения
 */
@Configuration
@ComponentScan("com.github.onechesz.axiomatikatesttask")
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement
@EnableWebMvc
public class SpringConfig {
    private final Environment environment;

    @Autowired
    public SpringConfig(Environment environment) {
        this.environment = environment;
    }

    /**
     * Конфигурация источника данных
     *
     * @return
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

        driverManagerDataSource.setDriverClassName(environment.getRequiredProperty("hibernate.driver_class"));
        driverManagerDataSource.setUrl(environment.getRequiredProperty("hibernate.connection.url"));
        driverManagerDataSource.setUsername(environment.getRequiredProperty("hibernate.connection.username"));
        driverManagerDataSource.setPassword(environment.getRequiredProperty("hibernate.connection.password"));

        return driverManagerDataSource;
    }

    /**
     * Конфигурация свойств hibernate
     *
     * @return
     */
    private @NotNull Properties hibernateProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));

        return properties;
    }

    /**
     * Создание синглтон-фабрики, которая отвечает за выдачу сессий для связи с БД
     *
     * @return
     */
    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();

        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("com.github.onechesz.axiomatikatesttask.entities");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());

        return localSessionFactoryBean;
    }

    /**
     * Менеджер транзакций
     *
     * @return
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();

        hibernateTransactionManager.setSessionFactory(localSessionFactoryBean().getObject());

        return hibernateTransactionManager;
    }
}
