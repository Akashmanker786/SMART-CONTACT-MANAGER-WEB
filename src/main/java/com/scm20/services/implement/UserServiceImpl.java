package com.scm20.services.implement;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.scm20.entities.User;
import com.scm20.helper.AppConstants;
import com.scm20.helper.ResourceNotFoundException;
import com.scm20.repositories.UserRepository;
import com.scm20.services.UserService;

@Component
public class UserServiceImpl implements UserService  {
     
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void deleteUser(String id) {
        User  user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(String id) {
    
        return userRepository.findById(id);
    }

    @Override
    public boolean isUserExist(String userId) {
       User user = userRepository.findById(userId).orElse(null);

        return user != null ?  true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public User saveUser(User user) {

        // Generating user id 
        String userId =  UUID.randomUUID().toString();
        user.setUserId(userId);

        // encrypting the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       
        // setting the role for user 

        user.setRoleList(List.of(AppConstants.ROLE_USER));

        return userRepository.save(user);
    }

    @Override
    public Optional<User> updateUser(User user) {
        
        User user2 = userRepository.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
       
        // now updating user 
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());

        // saving to database
        User save = userRepository.save(user2);        
        return Optional.ofNullable(save);
    }

    @Override
    public User getUserByEmail(String email) {
        
        return userRepository.findByEmail(email).orElse(null);
    }
  
}
