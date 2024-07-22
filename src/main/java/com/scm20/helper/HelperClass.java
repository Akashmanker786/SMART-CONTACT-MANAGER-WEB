package com.scm20.helper;


import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.var;

public class HelperClass {

    public static String getEmailOfLoggedInUser(Authentication authentication){

        // if user logged in hai google,github yaa fir facebook se to email aise nikalenge 
        if(authentication instanceof OAuth2AuthenticationToken){


          var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
          var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

          var oauth2User = (OAuth2User) authentication.getPrincipal();

          String username = "";

          if(clientId.equalsIgnoreCase("google")){

           // sign in with google
            System.out.println("getting email from google");
            username = oauth2User.getAttribute("email").toString();

          }
            
          else if( clientId.equalsIgnoreCase("github")){
           // for github
            System.out.println("getting email from github");

            username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString() : oauth2User.getAttribute("login").toString() + "@gmail.com";
          }
           
          return username;




        //    for facebook

        }

        else{         
            System.out.println("getting data from local database");                          // agar username aur password se login hai to ye 
            return authentication.getName();
        }

    }
}
