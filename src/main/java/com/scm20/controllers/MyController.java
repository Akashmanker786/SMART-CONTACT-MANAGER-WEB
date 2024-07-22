package com.scm20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm20.entities.User;
import com.scm20.formdata.UserRegister;
import com.scm20.helper.Message;
import com.scm20.helper.MessageType;
import com.scm20.services.implement.UserServiceImpl;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class MyController {
 
	@Autowired
	UserServiceImpl userServiceImpl;

	@RequestMapping("/")
	public String index( Model model) {
		model.addAttribute("developer", "Akash Manker");
		model.addAttribute("year", "2005");

		System.out.println("/   Home handler is running");
		return "home";
	}

	@RequestMapping("/home")
	public String homeController( Model model) {
		model.addAttribute("developer", "Akash Manker");
		model.addAttribute("year", "2005");

		System.out.println("/Home handler is running");
		return "home";
	}

	@RequestMapping("/about")
	public String aboutHandler(){
		return "about";
	}

	

	@RequestMapping("/services")
	public String servicesHandler( Model model){

        model.addAttribute("name", "Akashu Manker");
		model.addAttribute("id", "1009");
		return "services";
	}

	
	@RequestMapping("/contact")
	public String contactHandler(){
		return "contact";
	}

	@RequestMapping("/sign-in")
	public String sign_in_Handler(){
		return "sign_in";
	}


    @RequestMapping("/sign-up")
	public String sign_up_Handler(Model model){
		// sending empty object to save data
		UserRegister userRegister = new UserRegister();
		model.addAttribute("formData", userRegister);

		return "sign_up";
	}

   
	// register/sign-up a new user
	@PostMapping("/doRegister")
	public String register_user(@Valid @ModelAttribute("userData") UserRegister userData , BindingResult bResult , HttpSession session , Model model)  {

		// fetching the data from user 
		System.out.println("Register method is running ");
		System.out.println(userData);
		
		User user = new User();

		user.setName(userData.getName());
		user.setEmail(userData.getEmail());
		user.setPassword(userData.getPassword());
		user.setPhoneNumber(userData.getPhoneNumber());
		user.setAbout(userData.getAbout());
		user.setProfilePic("https://www.google.com/url?sa=i&url=https%3A%2F%2Fpngtree.com%2Fso%2Fdefault&psig=AOvVaw0_LH6nZLsHFCnu5J5zKG-N&ust=1715412435635000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCKi19b_HgoYDFQAAAAAdAAAAABAE");
		
	
		//validate the data of the form 
         if(bResult.hasErrors()){
			return "sign_up";
		 }  



		// save to db

        userServiceImpl.saveUser(user);

		// showing message 

		System.out.println("User Saved to db");
		
		Message message = Message.builder().content("Successfully Registered").type(MessageType.green).build();
		session.setAttribute("message", message);
		return "redirect:/sign-up";
	}
	

}
