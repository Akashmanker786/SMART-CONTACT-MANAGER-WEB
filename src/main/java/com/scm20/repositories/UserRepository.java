package com.scm20.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm20.entities.User;

public interface UserRepository extends JpaRepository<User,String> {

// custom finder methods 

  Optional<User> findByEmail(String email);

  Optional<User> findByEmailAndPassword(String email , String password);

}
