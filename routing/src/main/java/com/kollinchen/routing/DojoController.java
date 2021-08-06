package com.kollinchen.routing;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DojoController {
	@RequestMapping("/m")
    public String index(@RequestParam(value="q",required=false) String searchQuery) {
        return "You searched for: " + searchQuery;
    }
	@RequestMapping("/m/{dojo}")
    public String dojo(@PathVariable("dojo") String dojo){
		if(dojo.equals("burbank-dojo"))
		{
			return "Burbank Dojo is located in Southern California";
		}
		else if (dojo.equals("san-jose")) {
			return "SJ dojo is the headquarters";
		}
    	return "The " + dojo + " is awesome!";
    }
//	@RequestMapping("/{burbank-dojo}")
//    public String burbank(@PathVariable("burbank-dojo") String burbank-dojo){
//    	return "Burbank Dojo is located in Southern California'";
//    }
}
