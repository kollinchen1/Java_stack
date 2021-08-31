package com.kollinchen.eventsbelt.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kollinchen.eventsbelt.models.Event;
import com.kollinchen.eventsbelt.models.Message;
import com.kollinchen.eventsbelt.models.User;
import com.kollinchen.eventsbelt.services.EventService;
import com.kollinchen.eventsbelt.services.MessageService;
import com.kollinchen.eventsbelt.services.UserService;
import com.kollinchen.eventsbelt.validators.UserValidator;

@Controller
public class MainController {
	

	@Autowired
	private EventService eventServ;
	
	@Autowired
	private MessageService messServ;
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private UserValidator userValidator;
	@GetMapping("/")
    public String index(@ModelAttribute("userObj") User emptyUser,
    		Model model, 
    		HttpSession session,RedirectAttributes flashMessages
    ) {
		if (model.asMap().containsKey("error.userObj"))
        {
    		 model.addAttribute("org.springframework.validation.BindingResult.userObj",
    	                model.asMap().get("error.userObj"));
        }
		Long id = (Long) session.getAttribute("user_id");
		//if already logged in
		if(id != null)
			return "redirect:/events";
        return "index.jsp";
    }
	@PostMapping("/registration")
    public String registerUser(
    		@Valid @ModelAttribute("userObj") User filledUser, BindingResult result, 
    		HttpSession session,RedirectAttributes flashMessages
    ) {
    	userValidator.validate(filledUser, result);
        // if result has errors, return the registration page (don't worry about validations just now)
    	if(result.hasErrors()) {
//    		System.out.println(result);
    		flashMessages.addFlashAttribute("error.userObj", result);
    		flashMessages.addFlashAttribute("userObj", filledUser);
    		return "redirect:/";
    	}
        // else, save the user in the database, save the user id in session, and redirect them to the /home route
    	else {
    		User newUser = userServ.registerUser(filledUser);
    		session.setAttribute("user_id", newUser.getId());
    		return "redirect:/events";
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
    		return "redirect:/events";
    	}
        // else, add error messages and return the login page
    	else {
    		flashMessages.addFlashAttribute("errorLogin", "INVALID LOGIN");
    		return "redirect:/";
    	}
    }
    @GetMapping("/events")
    public String allEvents(
    		@ModelAttribute("eventObj") Event event,
    		HttpSession session,Model model,
    		RedirectAttributes flashMessages
    ) {
    	if(session.getAttribute("user_id") == null) {
			flashMessages.addFlashAttribute("error", "YOU MUST BE LOGGED IN TO ACCESS THE PAGE");
			return "redirect:/";
		}
    	if (model.asMap().containsKey("error.eventObj"))
        {
    		 model.addAttribute("org.springframework.validation.BindingResult.eventObj",
    	                model.asMap().get("error.eventObj"));
        }
		Long id = (Long) session.getAttribute("user_id");
		//if already logged in
		if(id == null)
			return "redirect:/";
		User user = userServ.findUserById(id);
		model.addAttribute("user",user);
		List<Event> allEvents = eventServ.allEvents();
		// PASS ALL EVENTS TO JSP
		model.addAttribute("events", allEvents);
        return "events.jsp";
    }
    @PostMapping("/create/event")
    public String createEvents(
    		@Valid @ModelAttribute("eventObj") Event event, BindingResult result, 
    		HttpSession session,RedirectAttributes flashMessages
    ) {
    	
    	if(result.hasErrors()) {
    		flashMessages.addFlashAttribute("error.eventObj", result);
    		flashMessages.addFlashAttribute("eventObj", event);
    	}
        // else, save the user in the database, save the user id in session, and redirect them to the /home route
    	else {
    		eventServ.saveEvent(event);		
    	}
    	return "redirect:/events";
    }
    @GetMapping("/events/{id}/edit")
    public String editEvent(
    		@PathVariable("id") Long event_id,Model model,HttpSession session,
    		RedirectAttributes flashMessages
    ) {
//    	System.out.println(session.getAttribute("user_id"));
//    	System.out.println(eventServ.findOneEvent(event_id).getHost());
    	Long eventid = eventServ.findOneEvent(event_id).getHost().getId();	
    	if(!session.getAttribute("user_id").equals(eventid)) {
			flashMessages.addFlashAttribute("errorBruteForce", "YOU ARE NOT THE HOST FOR THAT EVENT");
			return "redirect:/events";
		}
    	Event event = eventServ.findOneEvent(event_id);
	    model.addAttribute("eventObj",event);
	    return "/edit.jsp";
    }
    @PutMapping("/events/{id}/edit")
    public String editEventConfirm(
    		@PathVariable("id") Long event_id,
    		@Valid @ModelAttribute("eventObj") Event event, BindingResult result
    ) {
    	if(result.hasErrors()) {
    		return "/edit.jsp";
    	}
        // else, save the user in the database, save the user id in session, and redirect them to the /home route
    	else {
    		eventServ.updateEvent(event);
    		return "redirect:/events";
    	}
    	
    	
    }
    @GetMapping("/events/{id}/delete")
	public String delete(@PathVariable("id") Long event_id, HttpSession session,RedirectAttributes flashMessages) {
		// CHECK TO SEE IF A USER IS LOGGED IN
    	if(session.getAttribute("user_id") == null) {
			flashMessages.addFlashAttribute("error", "YOU MUST BE LOGGED IN TO ACCESS THE PAGE");
			return "redirect:/";
		}
    	Long eventid = eventServ.findOneEvent(event_id).getHost().getId();
    	if(!session.getAttribute("user_id").equals(eventid)) {
			flashMessages.addFlashAttribute("errorBruteForce", "YOU ARE NOT THE HOST FOR THAT EVENT");
			return "redirect:/events";
		}
		eventServ.deleteEvent(event_id);
		return "redirect:/events";
	}
    @RequestMapping("/events/{id}")
	public String userJoinEvent(
			@PathVariable("id") Long event_id,
			RedirectAttributes flashMessages,
			HttpSession session
	) {
    	if(session.getAttribute("user_id") == null) {
			flashMessages.addFlashAttribute("error", "YOU MUST BE LOGGED IN TO ACCESS THE PAGE");
			return "redirect:/";
		}
    	Long user_id = (Long) session.getAttribute("user_id");
    	Event oneEvent = eventServ.findOneEvent(event_id);
		User oneUser = userServ.findUserById(user_id);

//		oneUser.getEvents().add(oneEvent);
		oneEvent.getJoinedUsers().add(oneUser);
		// SAVE THE NEW INFORMATION IN THE DB
		eventServ.saveEvent(oneEvent);
		
    	
		return "redirect:/events";
    }
    @RequestMapping("/events/{id}/cancel")
   	public String userCancelEvent(
   			@PathVariable("id") Long event_id,
			RedirectAttributes flashMessages,
			HttpSession session
   	) {
    	if(session.getAttribute("user_id") == null) {
			flashMessages.addFlashAttribute("error", "YOU MUST BE LOGGED IN TO ACCESS THE PAGE");
			return "redirect:/";
		}
    	Long user_id = (Long) session.getAttribute("user_id");
    	Event oneEvent = eventServ.findOneEvent(event_id);
		User oneUser = userServ.findUserById(user_id);
//    	System.out.print(oneEvent.getJoinedUsers());
//    	System.out.print(oneUser.getEvents());
    	oneEvent.getJoinedUsers().remove(oneUser);
    	oneUser.getEvents().remove(oneEvent);
    	eventServ.saveEvent(oneEvent);
    	return "redirect:/events";
    }
    @GetMapping("/events/{id}/view")
    public String viewEvent(
    		@PathVariable("id") Long event_id,
    		@ModelAttribute("messageObj") Message message,
    		Model model,
    		HttpSession session,
    		RedirectAttributes flashMessages
    		
    ) {
    	if (model.asMap().containsKey("error.messageObj"))
        {
    		 model.addAttribute("org.springframework.validation.BindingResult.messageObj",
    	                model.asMap().get("error.messageObj"));
        }
    	Event oneEvent = eventServ.findOneEvent(event_id);
    	model.addAttribute("messages",oneEvent.getMessages());
    	model.addAttribute("event",oneEvent);
    	return "viewone.jsp";
    }
    
    @PostMapping("/events/message")
    public String addMessage(
    		@ModelAttribute("messageObj") @Valid Message message,
    		BindingResult result,
    		HttpSession session,
    		RedirectAttributes flashMessages
    ) {//http cannot be put next to model attribute!!!!!!!!!!
    	if(session.getAttribute("user_id") == null) {
			flashMessages.addFlashAttribute("error", "YOU MUST BE LOGGED IN TO ACCESS THE PAGE");
			return "redirect:/";
		}
    	
    	if(result.hasErrors()) {
    		flashMessages.addFlashAttribute("error.messageObj", result);
    		flashMessages.addFlashAttribute("messageObj", message);
    	}
    	else {
    		messServ.saveMessage(message);	
    	}
    	Long event_id = message.getEvent().getId();
    	return "redirect:/events/"+event_id+"/view";
    	
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	
        // invalidate session
    	session.invalidate();
        // redirect to login page
    	return "redirect:/";
    }
    
}
