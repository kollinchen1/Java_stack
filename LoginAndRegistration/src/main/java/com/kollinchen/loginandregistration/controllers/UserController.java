package com.kollinchen.loginandregistration.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kollinchen.loginandregistration.models.User;
import com.kollinchen.loginandregistration.services.UserService;
import com.kollinchen.loginandregistration.validators.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userServ;
	
	@Autowired
	private UserValidator userValidator;
	
	@GetMapping("/registration")
    public String registerForm(@ModelAttribute("userObj") User emprtyUser) {
        return "registrationPage.jsp";
    }
    @GetMapping("/login")
    public String login() {
        return "loginPage.jsp";
    }
    
    
    @PostMapping("/registration")
    public String registerUser(
    		@Valid @ModelAttribute("userObj") User filledUser, BindingResult result, 
    		HttpSession session,RedirectAttributes flashMessages
    ) {
    	userValidator.validate(filledUser, result);
        // if result has errors, return the registration page (don't worry about validations just now)
    	if(result.hasErrors()) {
    		flashMessages.addFlashAttribute("error", "INVALID FORMAT");
    		return "redirect:/registration";
//    		return "registrationPage.jsp";
    	}
        // else, save the user in the database, save the user id in session, and redirect them to the /home route
    	else {
    		User newUser = userServ.registerUser(filledUser);
    		session.setAttribute("user_id", newUser.getId());
    		return "redirect:/home";
    	}
    }
    
    @PostMapping("/login")
    public String loginUser(
    		@RequestParam("email") String email, @RequestParam("password") String password, 
    		Model model, 
    		HttpSession session,
    		RedirectAttributes flashMessages
    ) {
        // if the user is authenticated, save their user id in session
    	if(userServ.authenticateUser(email, password)) {
    		User loggedUser = userServ.findByEmail(email);
    		session.setAttribute("user_id", loggedUser.getId());
    		return "redirect:/home";
    	}
        // else, add error messages and return the login page
    	else {
    		flashMessages.addFlashAttribute("error", "INVALID LOGIN");
    		return "redirect:/login";
    	}
    }
    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {
    	// get user from session, save them in the model and return the home page
		Long id = (Long) session.getAttribute("user_id");
		if(id == null)
			return "redirect:/login";
		User user = userServ.findUserById(id);
		model.addAttribute("user",user);
		return "homePage.jsp";
        
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	
        // invalidate session
    	session.invalidate();
        // redirect to login page
    	return "redirect:/login";
    }
    
	
	
}