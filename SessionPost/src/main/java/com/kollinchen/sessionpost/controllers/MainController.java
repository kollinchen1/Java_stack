package com.kollinchen.sessionpost.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
	@RequestMapping("/")
	public String index(HttpSession session) {
		session.setAttribute("count", 0);
		return "index.jsp";
	}
	//@RequestMapping("/display")
	@GetMapping("display")
	public String display(Model model,HttpSession session) {
		if(session.getAttribute("count")==null) {
			return "redirect:/";
		}
		int count = (int) session.getAttribute("count");
		session.setAttribute("count", ++count);
		model.addAttribute("countContext",count);
		return "display.jsp";
	}
	//@RequestMapping(value = "/process",method=RequestMethod.POST)
	@PostMapping("/process")
	public String process(
			@RequestParam("email") String e,
			@RequestParam("password") String p
	) {
		return "redirect:/";
		
	}
	
}
