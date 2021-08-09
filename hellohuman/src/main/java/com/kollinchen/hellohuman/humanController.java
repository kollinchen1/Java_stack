package com.kollinchen.hellohuman;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class humanController {
	@RequestMapping("/")
    public String index(@RequestParam(value="first_name",required=false) String firstName,@RequestParam(value="last_name",required=false) String lastName) {
		String human = "Hello Human! \n\n Welcome to SpringBoot";
		String onlyFirst = "Hello "+firstName+"! \n\n Welcome to SpringBoot";
		String firstLast = "Hello "+firstName+" "+lastName+"! \n\n Welcome to SpringBoot";
		if(firstName == null && lastName ==null) {
			return "<html>\n" + "<header><title>Hello....Human"+"</title></header>\n" +
			          "<body>\n" + human+"\n</body>\n" + "</html>";
		}//?first_name=kollin&last_name=chen
		else if(lastName ==null)
			return "<html>\n" + "<header><title>Hello...."+firstName+"</title></header>\n" +
	          "<body>\n" + onlyFirst + "<a href=&quot/&quot>Go Back</a>"+"\n</body>\n" + "</html>";
		return "<html>\n" + "<header><title>Hello...."+firstName+" "+lastName+"</title></header>\n" +
		          "<body>\n" + firstLast + "<a href=/>Go Back</a>"+"\n</body>\n" + "</html>";
		
	}

}