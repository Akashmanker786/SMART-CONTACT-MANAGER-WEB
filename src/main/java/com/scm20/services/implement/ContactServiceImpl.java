package com.scm20.services.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm20.entities.Contact;
import com.scm20.helper.ResourceNotFoundException;
import com.scm20.repositories.ContactRepo;
import com.scm20.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    ContactRepo contactRepo;

    @Override
    public Contact save(Contact contact) {
        String  contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);      
    }


    @Override
    public Contact update(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<Contact> getAll() {
      return contactRepo.findAll();    
    }

    @Override
    public Contact getById(String id) {
        return contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact not found with given id = "+id));    
    }

    @Override
    public void delete(String id) {
       contactRepo.deleteById(id);  
    }

    @Override
    public List<Contact> search(String id, String email, String phoneNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public List<Contact> getByUserId(String id) {
       return contactRepo.findByUserId(id);    
    }


}
