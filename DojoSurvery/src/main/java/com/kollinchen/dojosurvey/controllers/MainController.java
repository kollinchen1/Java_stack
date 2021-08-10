package com.kollinchen.dojosurvey.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String index(HttpSession session) {
		session.removeAttribute("name");
		session.removeAttribute("dojo_location");
		session.removeAttribute("favorite_language");
		session.removeAttribute("comment");
		return "index.jsp";
	}
	@RequestMapping("/result")
	public String result(HttpSession session) {
		if(session.getAttribute("name")==null)
			return "redirect:/";
//		if(session.getAttribute("name").equals(""))
//	        session.setAttribute("name","nobody");
//		if(session.getAttribute("comment").equals(""))
//	        session.setAttribute("comment","Hi there, I am "+session.getAttribute("name"));
		return "result.jsp";
	}
	@RequestMapping("/ilovejava")
	public String javaPage(HttpSession session) {
		if(session.getAttribute("name")==null)
			return "redirect:/";
		return "java.jsp";
	}
	@PostMapping("/process")
	public String process(
			@RequestParam("name") String name,
			@RequestParam("dojo_location") String dojo_location,
			@RequestParam("favorite_language") String favorite_language,
			@RequestParam("comment") String comment,
			Model model,
			RedirectAttributes redirectAttributes,
			HttpSession session
	) {
		if(name.equals(""))
			name = "nobody";
		if(comment.equals(""))	
			comment = "Hi there, I am "+name;
		session.setAttribute("name",name);
		session.setAttribute("dojo_location",dojo_location);
		session.setAttribute("favorite_language",favorite_language);
		session.setAttribute("comment",comment);
		if(session.getAttribute("favorite_language").equals("Java")) {
			return "redirect:/ilovejava";
		}
		return "redirect:/result";
	}
}