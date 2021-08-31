package com.kollinchen.dojoandninja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kollinchen.dojoandninja.models.Dojo;
import com.kollinchen.dojoandninja.models.Ninja;
import com.kollinchen.dojoandninja.services.MainService;

@Controller
public class MainControllers {
	@Autowired
	private MainService mainService;
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/dojos/new";
	}
	@GetMapping("/dojos/new")
	public String index(@ModelAttribute("newDojo") Dojo emptyDojo,
			Model model,
			RedirectAttributes redirectAttributes
			) {
		//errors from postmappinig new dojo
		if (model.asMap().containsKey("errors.newDojo"))
        {
    		 model.addAttribute("org.springframework.validation.BindingResult.newDojo",
    	                model.asMap().get("errors.newDojo"));
        }
		return "index.jsp";
	}
	@PostMapping("/dojos/new")
	public String createDojo(
			@Valid @ModelAttribute("newDojo") Dojo newDojo,
			BindingResult result,
    		RedirectAttributes redirectAttributes
	) {
		if (result.hasErrors()) {
//			System.out.println(result);
        	redirectAttributes.addFlashAttribute("errors.newDojo", result);
            redirectAttributes.addFlashAttribute("newDojo", newDojo);
        } else {
        	mainService.saveDojo(newDojo);
        }
		
		return "redirect:/dojos/new";
	}
	@GetMapping("/ninjas/new")
	public String ninjas(
			@ModelAttribute("newNinja") Ninja emptyNinja,
			Model model,
			RedirectAttributes redirectAttributes
			) {
		if (model.asMap().containsKey("errors.newNinja"))
        {
    		 model.addAttribute("org.springframework.validation.BindingResult.newNinja",
    	                model.asMap().get("errors.newNinja"));
        }
		List<Dojo> allDojos = mainService.allDojos();
		model.addAttribute("allDojos", allDojos);
		return "ninjas.jsp";
	}
	@PostMapping("/ninjas/new")
	public String createNinja(
			@Valid @ModelAttribute("newNinja") Ninja newNinja,
			BindingResult result,
			RedirectAttributes redirectAttributes
			
	) {
		//errors from postmappinig new ninja
		if (result.hasErrors()) {
        	redirectAttributes.addFlashAttribute("errors.newNinja", result);
            redirectAttributes.addFlashAttribute("newNinja", newNinja);
            return "redirect:/ninjas/new";
        } else {
        	long id = newNinja.getDojoninjas().getId();
//        	System.out.println(id);
        	//create
        	mainService.saveNinja(newNinja);
        	return "redirect:/ninjas/"+id;
//        	return "redirect:/ninjas/";
        }
		
	}
	@GetMapping("/ninjas/{id}")
	public String oneDojo(
			@PathVariable("id") Long id,
			Model model
	) {
		//list one dojo, with all its ninjas
		Dojo dojo = mainService.findDojo(id);

		model.addAttribute("dojo", dojo);
		return "showone.jsp";
	}
	@GetMapping("/ninjas")
	public String allNinjasWithDojos(
			Model model
	) {
		List<Dojo> allDojos = mainService.allDojos();
		model.addAttribute("allDojos", allDojos);
		return "showall.jsp";
	}
	@GetMapping("/ninja/{id}")
	public String oneNinja(
			@PathVariable("id") Long id,
			Model model
	) {
		Ninja ninja = mainService.findNinja(id);
		Dojo d = ninja.getDojoninjas();
		model.addAttribute("ninja", ninja);
		model.addAttribute("dojo", d);
		return "oneninja.jsp";
	}

}
