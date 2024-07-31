package com.scm20;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.scm20.services.EmailService;


@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}


	@Autowired
    private EmailService emailService;
    
	@Test
    void sendEmail() {

        emailService.sendEmail("akashmanker722@gmail.com", "just for testing Third  time...😃",
                "spring boot project okay ...");
    }

}
