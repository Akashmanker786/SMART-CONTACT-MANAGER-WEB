package com.scm20.services;

import java.util.List;

import com.scm20.entities.Contact;

public interface ContactService {

    Contact save(Contact contact);

    Contact update(Contact contact);

    List<Contact> getAll();

    Contact getById(String id);

    void delete(String id);

    List<Contact> search(String id , String email , String phoneNumber);

    List<Contact> getByUserId(String id);

}
