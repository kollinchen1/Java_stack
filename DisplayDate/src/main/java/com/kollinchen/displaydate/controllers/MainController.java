package com.kollinchen.displaydate.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MainController {
	@RequestMapping("/")
	public String index(HttpSession session) {
		return "index.jsp";
	}
	@RequestMapping("/date")
	public String getDate(Model model) {
		Date current = new Date();
//		String currentDate = 
		String currentDate = new SimpleDateFormat("E, 'the' d 'of' MMMM, yyyy ").format(current);
		model.addAttribute("currentDate",currentDate);
		//Saturday, the 22 of January, 2028
		return "date.jsp";
	}
	@RequestMapping("/time")
	public String getTime(Model model) {
		Date current = new Date();
		String currentTime = new SimpleDateFormat("h:mm a").format(current);
		model.addAttribute("currentTime",currentTime);
		//11:30 PM
		return "time.jsp";
	}
	
}

