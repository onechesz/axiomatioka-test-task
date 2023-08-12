package com.github.onechesz.axiomatikatesttask.configs;

import com.github.onechesz.axiomatikatesttask.services.UserDetailsService;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Отвечает за безопасность Spring приложения, доступ к страницам в зависимости от роли и статуса авторизации, а также
 * шифрование паролей
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Цепочка фильтров, разделяющая доступ к приложению в зависимости от роли и статуса авторизации
     *
     * @param httpSecurity
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry.requestMatchers("/manage/login").anonymous();
            authorizationManagerRequestMatcherRegistry.requestMatchers("/manage/**").hasAnyRole("USER", "ADMIN");
            authorizationManagerRequestMatcherRegistry.anyRequest().permitAll();
        }).formLogin(httpSecurityFormLoginConfigurer -> {
            httpSecurityFormLoginConfigurer.loginPage("/manage/login");
            httpSecurityFormLoginConfigurer.loginProcessingUrl("/login");
            httpSecurityFormLoginConfigurer.failureUrl("/manage/login");
            httpSecurityFormLoginConfigurer.defaultSuccessUrl("/manage", true);
        }).logout(httpSecurityLogoutConfigurer -> {
            httpSecurityLogoutConfigurer.logoutUrl("/logout");
            httpSecurityLogoutConfigurer.logoutSuccessUrl("/");
        }).userDetailsService(userDetailsService).httpBasic(Customizer.withDefaults()).build();
    }

    /**
     * Кодировщик паролей
     *
     * @return
     */
    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
