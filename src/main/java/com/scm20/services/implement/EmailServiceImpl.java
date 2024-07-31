package com.scm20.services.implement;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.MimeMailMessage;
// import org.springframework.mail.javamail.MimeMessagePreparator;
// import org.springframework.stereotype.Service;

// import com.scm20.services.EmailService;

// @Service
// public class EmailServiceImpl implements EmailService{
   
//     Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

//     @Autowired
//     private JavaMailSender mailSender;

//     @Override
//     public void sendEmail(String to, String subject, String body) {

//         System.out.println("sendEmail method is running ============================");
       
        
//         SimpleMailMessage message = new SimpleMailMessage();
                 

//         message.setTo(to);
//         message.setSubject(subject);
//         message.setText(body);

//         message.setFrom("akashmanker3@gmail.com");
        
//         mailSender.send(message);

//         logger.info("Email sent successfully to {}", to);



       

       

//     }

    
    
    
    
    
    
    
    
    
//     @Override
//     public void sendEmailWithHtml() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'sendEmailWithHtml'");
//     }

//     @Override
//     public void sendEmailWithAttachment() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'sendEmailWithAttachment'");
//     }


// }



import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.scm20.services.EmailService;


@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.properties.domain_name}")
    private String domainName;

    private JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;

    }

    @Override
    public void sendEmail(String to, String subject, String message) {


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom(domainName);

        javaMailSender.send(simpleMailMessage);

    }

    

    @Override
    public void sendEmailWithHtml() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendEmailWithHtml'");
    }

    @Override
    public void sendEmailWithAttachment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendEmailWithAttachment'");
    }

}









