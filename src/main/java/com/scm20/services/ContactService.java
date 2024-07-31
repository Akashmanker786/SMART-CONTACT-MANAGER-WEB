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


    List<Contact> getByUserId(String id);

    Page<Contact> getByUser(User user , int page,int size ,String sortBy,String direction);



    Page<Contact> searchByName(User user ,String keyword , int noOfPage , int sizeOfPage , String sortBy , String direction);

    Page<Contact> searchByEmail(User user,String keyword , int noOfPage , int sizeOfPage , String sortBy , String direction);

    Page<Contact> searchByPhoneNumber(User user,String keyword , int noOfPage , int sizeOfPage , String sortBy , String direction);


}
