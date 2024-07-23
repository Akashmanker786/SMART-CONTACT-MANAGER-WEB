package com.scm20.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.scm20.entities.Contact;
import com.scm20.entities.User;

public interface ContactService {

    Contact save(Contact contact);

    Contact update(Contact contact);

    List<Contact> getAll();

    Contact getById(String id);

    void delete(String id);

    List<Contact> search(String id , String email , String phoneNumber);

    List<Contact> getByUserId(String id);

    Page<Contact> getByUser(User user , int page,int size ,String sortBy,String direction);

}
