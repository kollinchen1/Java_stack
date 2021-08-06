package com.kollinchen.routing;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coding")
public class CodingController {
	@RequestMapping("/")
    // 3. Method that maps to the request route above
    public String index() { // 3
            return "Hello Coding Dojo!";
    }
    @RequestMapping("/python")
    //Method that maps to the request route above
    public String python() { // 3
            return "Python/Django was awesome!";
    }
    @RequestMapping("/java")
    //Method that maps to the request route above
    public String java() { // 3
            return "Java/Spring is better!";
    }
}
