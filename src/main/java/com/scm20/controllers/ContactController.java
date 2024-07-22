package com.scm20.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.scm20.entities.Contact;
import com.scm20.entities.User;
import com.scm20.formdata.ContactFormData;
import com.scm20.helper.HelperClass;
import com.scm20.helper.Message;
import com.scm20.helper.MessageType;
import com.scm20.services.ContactService;
import com.scm20.services.ImageService;
import com.scm20.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    Logger logger = LoggerFactory.getLogger(ContactController.class);


    @Autowired
    ImageService imageService;


    @Autowired
    ContactService contactService;

    @Autowired
    UserService userService;

// method to show the view of add_contact form with the blank object
@RequestMapping("/add")
public String addContact(Model model){
    
    ContactFormData contactFormData = new ContactFormData();
    model.addAttribute("formData" , contactFormData);
    return "user/add_contact";
}


// method to save add_contact form data

@RequestMapping(value = "/add" , method = RequestMethod.POST)
public String saveContact(@Valid @ModelAttribute("contactFormData") ContactFormData contactFormData , BindingResult result , Authentication authentication , HttpSession session , @RequestParam("picture") MultipartFile contactImage){
    
    // validate form
    // if(result.hasErrors()){

    //     return "user/add_contact";
    // }
    // validation logic

    
    //  process picture

    // creating public id and uploading
    String fileName = UUID.randomUUID().toString();
    
    String fileURL = imageService.uploadImage(contactImage,fileName);


     String username = HelperClass.getEmailOfLoggedInUser(authentication);

     User user = userService.getUserByEmail(username);

    // contactFormData -> contacts
    Contact contact = new Contact();
    
    contact.setName(contactFormData.getName());
    contact.setEmail(contactFormData.getEmail());
    contact.setAddress(contactFormData.getAddress());
    contact.setDiscription(contactFormData.getDiscription());
    contact.setPhoneNumber(contactFormData.getPhoneNumber());
    contact.setFavourite(contactFormData.getFavourite());
    contact.setUser(user);
    contact.setPicture(fileURL);
    contact.setCloudinaryImagePublicId(fileName);



    System.out.println("Save contact method running...............");
    contactService.save(contact);
    System.out.println("Contact Saved to DB successfully");

    session.setAttribute("message",Message.builder().content("Contact Saved Successfully").type(MessageType.green).build());
    

    return "redirect:/user/contacts/add";
}



}
