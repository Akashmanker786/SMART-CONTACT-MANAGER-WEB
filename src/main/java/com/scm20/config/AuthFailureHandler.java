package com.scm20.config;

import java.io.IOException;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.scm20.helper.Message;
import com.scm20.helper.MessageType;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthFailureHandler implements AuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        
                if(exception instanceof DisabledException){
                   HttpSession session = request.getSession();
                   session.setAttribute("message", Message.builder().content("Your Account is Disabled !! Check Email for verification code").type(MessageType.red).build());
                  
                   response.sendRedirect("/sign-in");
                }

                else{
                    response.sendRedirect("/sign-in?error=true");
                }
        
            }


}
