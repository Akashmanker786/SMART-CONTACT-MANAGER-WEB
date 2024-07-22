package com.scm20.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.scm20.entities.Contact;
import com.scm20.entities.User;

public interface ContactRepo extends JpaRepository<Contact,String> {


    List<Contact> findByUser(User user);

    @Query("SELECT c FROM Contact c WHERE c.user.userId = :userId")
    List<Contact> findByUserId(@Param("userId") String userId);

}
