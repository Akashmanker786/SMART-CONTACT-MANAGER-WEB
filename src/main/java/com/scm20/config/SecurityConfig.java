package com.scm20.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm20.services.implement.SecurityUserDetailServiceCustom;

@Configuration
public class SecurityConfig {

    @Autowired
    AuthFailureHandler authFailureHandler;

    @Autowired
    SecurityUserDetailServiceCustom securityUserDetailServiceCustom;


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(securityUserDetailServiceCustom);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        System.out.println("authentication provider method is running");
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
            System.out.println("security filter chain method running");
        });

        httpSecurity.formLogin(formLogin -> {
            formLogin.loginPage("/sign-in");
            formLogin.loginProcessingUrl("/processLogin");
            formLogin.successForwardUrl("/user/profile");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");


            // AuthenticationFailureHandler
            formLogin.failureHandler(authFailureHandler);
        });



        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.logout(formLogout -> {
            formLogout.logoutUrl("/do-logout");
            formLogout.logoutSuccessUrl("/sign-in?logout=true");
        });

       
        httpSecurity.oauth2Login(Customizer.withDefaults());



        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("password encoder method running");
        return new BCryptPasswordEncoder();
    }
}
