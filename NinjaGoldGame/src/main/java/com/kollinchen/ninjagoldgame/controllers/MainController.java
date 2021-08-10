package com.kollinchen.ninjagoldgame.controllers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kollinchen.ninjagoldgame.services.Message;

@Controller
public class MainController {
	static String[] suffixes =
	     {  "0th",  "1st",  "2nd",  "3rd",  "4th",  "5th",  "6th",  "7th",  "8th",  "9th",
	       "10th", "11th", "12th", "13th", "14th", "15th", "16th", "17th", "18th", "19th",
	       "20th", "21st", "22nd", "23rd", "24th", "25th", "26th", "27th", "28th", "29th",
	       "30th", "31st" };
	ArrayList<Message> messages = new ArrayList<Message>();
	int count = 0;
	Random random = new Random();
	@RequestMapping("/")
	public String reroute() {
		return "redirect:/Gold";
	}
	@RequestMapping("/Gold")
	public String index(Model model,HttpSession session) {
		session.setAttribute("count", count);
		model.addAttribute("count",session.getAttribute("count"));
		model.addAttribute("messages", session.getAttribute("messages"));
		return "gold.jsp";
	}
	@RequestMapping("/reset")
	public String reset(Model model,HttpSession session) {
		messages = new ArrayList<Message>();
		count = 0;
		session.setAttribute("count", count);
		session.setAttribute("messages", messages);
		return "redirect:/Gold";
	}
	
	@RequestMapping("/prison")
	public String prison(HttpSession session) {
		if(count<-60)
		{
			session.setAttribute("count", count);
			session.setAttribute("messages", messages);
			return "prison.jsp";
		}
		return "redirect:/Gold";
	}
	
	@PostMapping("/process/{item}")
	public String process(
			@PathVariable("item") String item,
			HttpSession session
	) {
        
        int temp = 0;
		switch(item){
			//(earns 10-20 gold)
			case"farm": 
				temp = random.nextInt(20 - 10) + 10;
				break;
			//(earns 5-10 gold)
			case"cave":	
				temp = random.nextInt(10 - 5) + 5;
				break;
			//(earns 2-5 gold)	
			case"house":
				temp = random.nextInt(5 - 2) + 2;
				break;
			//(earns/takes 0-50 gold)	
			case"casino":
				temp = random.nextInt(50 + 50) - 50;
				break;
			//loses 5-20
			case"spa":
				temp = random.nextInt(20 - 5) + 5;
				temp*=-1;
				break;
			case"dig":
				temp = 1;
				break;
			case"laundry":
				temp = random.nextInt(3 - 1) + 1;
				break;
			case"gamble":
				temp = random.nextInt(100 + 100) - 100;
				break;
			//int number = random.nextInt(max - min) + min;
		}
		count+=temp;
		
		String currentDate = findCurrentDate();
		String winLost = (temp<0)? "lost":"earned";
		String message = "You entered a "+item+" and "+winLost+" "+Math.abs(temp)+" gold.";
		if(temp<0) {
			message+=".. Ouch";
		}
		message+=" ("+currentDate+")";
		Message newMes = new Message(message,temp);
		messages.add(newMes);
		session.setAttribute("count", count);
		session.setAttribute("messages", messages);
		if(count<-60) {
			return "redirect:/prison";
		}
		return "redirect:/Gold";
	}
	//helper Method
	public String findCurrentDate() {
		Date current = new Date();
		SimpleDateFormat formatDayOfMonth  = new SimpleDateFormat("d");
		int day = Integer.parseInt(formatDayOfMonth.format(current));
		String dayStr = suffixes[day];
		System.out.print(dayStr);
		String currentDate = new SimpleDateFormat("MMMM '"+dayStr+"' yyyy, h:mm a ").format(current);
		
		return currentDate;
	}
	
	
}