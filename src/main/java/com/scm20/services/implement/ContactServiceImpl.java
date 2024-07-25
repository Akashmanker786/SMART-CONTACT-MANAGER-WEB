package com.scm20.services.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm20.entities.Contact;
import com.scm20.entities.User;
import com.scm20.helper.ResourceNotFoundException;
import com.scm20.repositories.ContactRepo;
import com.scm20.services.ContactService;

import lombok.var;

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
    public List<Contact> getByUserId(String id) {
       return contactRepo.findByUserId(id);    
    }


    @Override
    public Page<Contact> getByUser(User user , int page,int size ,String sortBy,String direction) {
    
        Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(page,size,sort);
        return contactRepo.findByUser(user,pageable);
    }


    @Override
    public Page<Contact> searchByName(User user,String keyword, int noOfPage, int sizeOfPage, String sortBy, String direction) {
       
        Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(noOfPage, sizeOfPage,sort);

        return contactRepo.findByUserAndNameContaining(user,keyword,pageable);
    }


    @Override
    public Page<Contact> searchByEmail(User user,String keyword, int noOfPage, int sizeOfPage, String sortBy, String direction) {
       
        Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(noOfPage, sizeOfPage,sort);

        return contactRepo.findByUserAndEmailContaining(user,keyword,pageable);
  
    }


    @Override
    public Page<Contact> searchByPhoneNumber(User user ,String keyword, int noOfPage, int sizeOfPage, String sortBy,
            String direction) {
               
                Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

                var pageable = PageRequest.of(noOfPage, sizeOfPage,sort);
        
                return contactRepo.findByUserAndPhoneNumberContaining(user,keyword,pageable);
          

            }





}
