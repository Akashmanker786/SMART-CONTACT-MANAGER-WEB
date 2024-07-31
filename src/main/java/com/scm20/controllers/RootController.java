package com.scm20.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm20.entities.User;
import com.scm20.helper.HelperClass;
import com.scm20.services.UserService;

@ControllerAdvice
public class RootController {

    Logger logger = LoggerFactory.getLogger(RootController.class);

    @Autowired
    UserService userService;

     @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {

        if(authentication == null){

            return;
        }
        System.out.println("adding logged in user inforamtion to model");

        String username = HelperClass.getEmailOfLoggedInUser(authentication);

        logger.info("Name is = " + username);

        User user = userService.getUserByEmail(username);

        model.addAttribute("loggedInUser", user);
        System.out.println("name of user = " + user.getName());
        System.out.println("phone of user = " + user.getPhoneNumber());
    }

}
