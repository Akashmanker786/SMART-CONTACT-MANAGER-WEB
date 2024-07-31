package com.scm20.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.scm20.repositories.UserRepository;

@Component
public class SecurityUserDetailServiceCustom implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      
        // user ko load karenge 
        System.out.println("load  method running of UserDetailsService");

      
        return userRepository.findByEmail(username).orElseThrow(()->  new UsernameNotFoundException("User not found with this email -:"+username));


    }

}
