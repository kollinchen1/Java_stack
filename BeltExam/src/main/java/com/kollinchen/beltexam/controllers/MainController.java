package com.kollinchen.beltexam.controllers;

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

import com.kollinchen.beltexam.models.Course;
import com.kollinchen.beltexam.models.User;
import com.kollinchen.beltexam.services.CourseService;
import com.kollinchen.beltexam.services.UserService;
import com.kollinchen.beltexam.validators.UserValidator;

import antlr.debug.Event;

@Controller
public class MainController {
	@Autowired
	private UserService userServ;
	@Autowired
	private CourseService courseServ;

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
			return "redirect:/courses";
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
    		return "redirect:/courses";
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
    		return "redirect:/courses";
    	}
        // else, add error messages and return the login page
    	else {
    		flashMessages.addFlashAttribute("errorLogin", "INVALID LOGIN");
    		return "redirect:/";
    	}
    }
    @GetMapping("/courses")
    public String allCourses(
    		Model model,
    		HttpSession session
    ) {
    	Long id = (Long) session.getAttribute("user_id");
		//if already logged in
		if(id == null)
			return "redirect:/";
		User user = userServ.findUserById(id);
		model.addAttribute("user",user);
		List<Course> allCourses = courseServ.allCourses();
		// PASS ALL EVENTS TO JSP
		model.addAttribute("courses", allCourses);
    	return "courses.jsp";
    }
    @GetMapping("/courses/new")
    public String createCoursePage(
    		 @ModelAttribute("courseObj") Course course, BindingResult result, 
     		HttpSession session,RedirectAttributes flashMessages,Model model
    ) {
    	if (model.asMap().containsKey("error.courseObj"))
        {
    		 model.addAttribute("org.springframework.validation.BindingResult.courseObj",
    	                model.asMap().get("error.courseObj"));
        }
    	Long id = (Long) session.getAttribute("user_id");
		//if not already logged in
		if(id == null)
			return "redirect:/";
		
    	return "createcourse.jsp";
    }
    @PostMapping("/courses/new")
    public String createCourse(
    		@Valid @ModelAttribute("courseObj") Course course, BindingResult result, 
    		HttpSession session,RedirectAttributes flashMessages
    ) {
    	
    	if(result.hasErrors()) {
    		flashMessages.addFlashAttribute("error.courseObj", result);
    		flashMessages.addFlashAttribute("courseObj", course);
    		return "redirect:/courses/new";
    	}
        // else, save the user in the database, save the user id in session, and redirect them to the /home route
    	else {
    		courseServ.saveCourse(course);
    		return "redirect:/courses";
    	}
    	
    }
    @RequestMapping("/add/{id}")
    public String addCourse(
    		@PathVariable("id") Long course_id,
    		HttpSession session
    ) {
    	Long user_id = (Long) session.getAttribute("user_id");
		//if not already logged in
		if(user_id == null)
			return "redirect:/";
		User user = userServ.findUserById(user_id);
		Course course = courseServ.findOneCourse(course_id);
		course.getJoinedUsers().add(user);
		courseServ.saveCourse(course);
		
		
    	return "redirect:/courses";
    }
    @GetMapping("/courses/{id}")
    public String oneCourse(
    		@PathVariable("id") Long course_id,
    		HttpSession session,
    		Model model
    ) {
    	Long user_id = (Long) session.getAttribute("user_id");
		//if not already logged in
		if(user_id == null)
			return "redirect:/";
    	Course course = courseServ.findOneCourse(course_id);
    	User currentUser = userServ.findUserById(user_id);
    	model.addAttribute("course",course);
    	model.addAttribute("currentUser",currentUser);
    	return "onecourse.jsp";
    }
    @GetMapping("/courses/{id}/edit")
    public String editEvent(
    		@PathVariable("id") Long course_id,
    		Model model,
    		HttpSession session,
    		RedirectAttributes flashMessages
    ) {
//    	if (model.asMap().containsKey("error.courseObj"))
//        {
//    		 model.addAttribute("org.springframework.validation.BindingResult.courseObj",
//    	                model.asMap().get("error.courseObj"));
//        }
    	Course course = courseServ.findOneCourse(course_id);
	    model.addAttribute("courseObj",course);
	    return "/edit.jsp";
    }
    @PutMapping("/courses/{id}/edit")
    public String editEventConfirm(
    		@PathVariable("id") Long course_id,
    		@Valid @ModelAttribute("courseObj") Course course, 
    		BindingResult result,
    		HttpSession session,
    		RedirectAttributes flashMessages
    ) {
    	Course oldCourse  = courseServ.findOneCourse(course_id);
    	if(result.hasErrors()) {
    		System.out.print(result);
    		return "/edit.jsp";
    		
    	}
    	
        // else, save the user in the database, save the user id in session, and redirect them to the /home route
    	else {
    		course.setJoinedUsers(oldCourse.getJoinedUsers());
    		courseServ.updateCourse(course);
    		return "redirect:/courses";
    	}
    	
    	
    }
    @RequestMapping("/remove/{id}")
    public String removeMyself(
    		@PathVariable("id") Long course_id,
        	HttpSession session
    ) {
    	Long user_id = (Long) session.getAttribute("user_id");
		//if not already logged in
		if(user_id == null)
			return "redirect:/";
    	Course course = courseServ.findOneCourse(course_id);
    	User currentUser = userServ.findUserById(user_id);
    	course.getJoinedUsers().remove(currentUser);
    	courseServ.saveCourse(course);
    	return "redirect:/courses/"+course_id;
    }
    @GetMapping("/courses/{id}/delete")
	public String delete(@PathVariable("id") Long course_id, HttpSession session,RedirectAttributes flashMessages) {
		// CHECK TO SEE IF A USER IS LOGGED IN
    	Long user_id = (Long) session.getAttribute("user_id");
		//if not already logged in
		if(user_id == null)
			return "redirect:/";
		courseServ.deleteCourse(course_id);
		return "redirect:/courses";
	}
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	
        // invalidate session
    	session.invalidate();
        // redirect to login page
    	return "redirect:/";
    }
}
