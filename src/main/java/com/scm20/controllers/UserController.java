package com.scm20.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm20.entities.User;
import com.scm20.helper.HelperClass;
import com.scm20.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";

    }

    @RequestMapping("/profile")
    public String userProfile(Authentication authentication, Model model) {

        return "user/profile";

    }

}
