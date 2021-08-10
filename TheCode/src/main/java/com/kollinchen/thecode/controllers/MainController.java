package com.kollinchen.thecode.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String index(HttpSession session) {
		session.removeAttribute("inside");
		return "index.jsp";
	}
	@PostMapping("/process")
	public String process(
			@RequestParam("code") String code,
			RedirectAttributes redirectAttributes,
			HttpSession session
	) {
		if(!code.equals("bushido")) {
			redirectAttributes.addFlashAttribute("error", "You must train Harder!");
			return "redirect:/";
		}
		session.setAttribute("inside", 1);
		return "redirect:/code";
		
	}
	@GetMapping("/code")
	public String displayCode(
			RedirectAttributes redirectAttributes,
			HttpSession session) {
		if(session.getAttribute("inside")==null) {
			redirectAttributes.addFlashAttribute("error", "You must enter the code");
			return "redirect:/";
		}
		return "code.jsp";
	}
	
}
