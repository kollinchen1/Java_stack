package com.kollinchen.counter.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
		
		
	@RequestMapping("/")
	public String index(HttpSession session) {
			if(session.getAttribute("count") == null ) {
				session.setAttribute("count", 0);
			}
			int count = (int) session.getAttribute("count");
			session.setAttribute("count", ++count);
			
			return "index.jsp";
	}
	@GetMapping("/counter")
	public String getCount(HttpSession session, Model model) {
		if(session.getAttribute("count") == null) {
			return "redirect:/";
		}
		int count = (int) session.getAttribute("count");
		model.addAttribute("countContext", count);
		return "counter.jsp";
	}
	@RequestMapping("/divide2")
	public String divideTwo(HttpSession session, Model model){
		int count = (int) session.getAttribute("count");
		session.setAttribute("count", count/2);	
		model.addAttribute("countContext", count);
		return "redirect:/counter";
	}
	@RequestMapping("/reset")
	public String reset(HttpSession session, Model model){
		session.setAttribute("count", 0);	
		return "redirect:/";
	}
		
		
		
}

