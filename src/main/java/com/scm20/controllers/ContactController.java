package com.scm20.controllers;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.scm20.entities.Contact;
import com.scm20.entities.User;
import com.scm20.formdata.ContactFormData;
import com.scm20.formdata.SearchFormData;
import com.scm20.helper.AppConstants;
import com.scm20.helper.HelperClass;
import com.scm20.helper.Message;
import com.scm20.helper.MessageType;
import com.scm20.services.ContactService;
import com.scm20.services.ImageService;
import com.scm20.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.var;

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


    private List<Contact> byUser;

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



  @RequestMapping()
  public String viewContacts(Authentication authentication , Model  model,
    @RequestParam(value = "page" , defaultValue = "0") int page,
    @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE +"") int size,
    @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
    @RequestParam(value = "direction", defaultValue="asc") String direction 
      
  ){

    String email = HelperClass.getEmailOfLoggedInUser(authentication);

    User user  = userService.getUserByEmail(email);

      Page<Contact> pageContact = contactService.getByUser(user , page , size , sortBy , direction);

    model.addAttribute("pageContact",pageContact);

    model.addAttribute("pageSize",AppConstants.PAGE_SIZE);

    model.addAttribute("searchFormData", new SearchFormData());

    return "user/view_contacts";
  }



  // controller to handle search
  @RequestMapping("/search")
  public String searchHandler(
    @ModelAttribute SearchFormData searchFormData,
    @RequestParam(value = "page" , defaultValue = "0") int page,
    @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
    @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
    @RequestParam(value = "direction", defaultValue="asc") String direction,
    Model model,
    Authentication authentication
    ){

      User user = userService.getUserByEmail( HelperClass.getEmailOfLoggedInUser(authentication));
      
      Page<Contact> pageContact = null;

      if(searchFormData.getField().equalsIgnoreCase("name")){
        pageContact = contactService.searchByName(user,searchFormData.getKeyword(), page, size, sortBy, direction);
      }
      else if (searchFormData.getField().equalsIgnoreCase("email")) {
        pageContact = contactService.searchByEmail(user,searchFormData.getKeyword(), page, size, sortBy, direction);
      }
      else if (searchFormData.getField().equalsIgnoreCase("phoneNumber")) {
        pageContact = contactService.searchByPhoneNumber(user,searchFormData.getKeyword(), page, size, sortBy, direction);
      }

      model.addAttribute("pageContact",pageContact);

      logger.info("pageContact ==== "+pageContact);

      model.addAttribute("searchFormData",searchFormData);

      model.addAttribute("pageSize",size);

    

    return "user/search";
  }


  // handler for deleting the contact
  @RequestMapping("/delete/{contactId}")
  public String deleteContact(@PathVariable("contactId") String contactId){
    contactService.delete(contactId);
    return "redirect:/user/contacts";
  }



  // handler to show updatecontact view 
  @RequestMapping("/updateView/{contactId}")
  public String updateView(@PathVariable("contactId") String contactId , Model model){
    
    var contact = contactService.getById(contactId);

    ContactFormData contactFormData = new ContactFormData();

    contactFormData.setName(contact.getName());
    contactFormData.setEmail(contact.getEmail());
    contactFormData.setPhoneNumber(contact.getPhoneNumber());
    contactFormData.setAddress(contact.getAddress());
    contactFormData.setDiscription(contact.getDiscription());
    contactFormData.setFavourite(contact.getFavourite());
    contactFormData.setPicture(contact.getPicture());


    model.addAttribute("formData",contactFormData);
    model.addAttribute("contactId",contactId);

    return "user/updateFormView";
  }


  // handler for updating the contact
  @RequestMapping(value = "updateContact/{contactId}" ,method = RequestMethod.POST)
  public String updateContact(@PathVariable("contactId") String contactId,
  @ModelAttribute ContactFormData contactFormData, 
  Model model){

    var contact = contactService.getById(contactId);

    contact.setId(contactId);
    contact.setName(contactFormData.getName());
    contact.setEmail(contactFormData.getEmail());
    contact.setPhoneNumber(contactFormData.getPhoneNumber());
    contact.setAddress(contactFormData.getAddress());
    contact.setDiscription(contactFormData.getDiscription());
    contact.setFavourite(contactFormData.getFavourite());

    // processing image
    if(contactFormData.getContactImage()!=null && !contactFormData.getContactImage().isEmpty()){
      logger.info("contactImage is not null");
      String fileName = UUID.randomUUID().toString();
      String fileURL = imageService.uploadImage(contactFormData.getContactImage(), fileName);
      contact.setCloudinaryImagePublicId(fileName);
      contact.setPicture(fileURL);
      contactFormData.setPicture(fileURL);
    }
    else{
      logger.info("contact image is null");
    }
    

    var Updatedcon = contactService.update(contact);

    logger.info("updated contact {}",Updatedcon);

    

    return "redirect:/user/contacts/updateView/"+contactId;
  }

}