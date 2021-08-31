package com.kollinchen.languages.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kollinchen.languages.models.Language;
import com.kollinchen.languages.services.LanguageService;


@Controller
public class LanguagesController {
	private final LanguageService languageService;
	    
    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }
    
    
    @RequestMapping(value="/languages", method = RequestMethod.GET)
    public String index(
    		Model model,
    		@ModelAttribute("newLanguage") Language newLanguage,
    		RedirectAttributes redirectAttributes
    		) {
    	if (model.asMap().containsKey("errors.newLanguage"))
        {
    		 model.addAttribute("org.springframework.validation.BindingResult.newLanguage",
    	                model.asMap().get("errors.newLanguage"));
//        	System.out.println(model.asMap().get("errors.newLanguage"));
        }
        List<Language> allLanguages = languageService.allLanguages();
        model.addAttribute("languages", allLanguages);
        return "/index.jsp";
    }
    @RequestMapping(value="/languages", method=RequestMethod.POST)
    public String create(
    		@Valid @ModelAttribute("newLanguage") Language newLanguage, 
    		BindingResult result,
    		RedirectAttributes redirectAttributes
    		) {
        if (result.hasErrors()) {
//        	System.out.println(result);
        	redirectAttributes.addFlashAttribute("errors.newLanguage", result);
            redirectAttributes.addFlashAttribute("newLanguage", newLanguage);
        	return "redirect:/languages";
        } else {
        	languageService.createLanguage(newLanguage);
            return "redirect:/languages";
        }
    }
    @RequestMapping(value="/languages/{id}", method = RequestMethod.GET)
	 public String view(@PathVariable("id") Long id,Model model) {
	     Language language = languageService.findLanguage(id);
	     model.addAttribute(language);
	     return "/view.jsp";
	 }
    @RequestMapping(value="/languages/{id}/edit", method = RequestMethod.GET)
	 public String edit(@PathVariable("id") Long id,Model model) {
	     Language language = languageService.findLanguage(id);
	     model.addAttribute(language);
	     return "/edit.jsp";
	 }
    @RequestMapping(value="/languages/{id}", method=RequestMethod.PUT)
    public String edit(@PathVariable("id") Long id, @Valid @ModelAttribute("language") Language editLanguage, BindingResult result) {
    	 if (result.hasErrors()) {
             return "/edit.jsp";
         } else {
        	 languageService.updateLanguage(editLanguage);
    	     //bookService.updateBook(id, book.getTitle(), book.getDescription(), book.getLanguage(), book.getNumberOfPages());
             return "redirect:/languages/{id}/";
         }	
    	
    }
    @RequestMapping(value="/languages/{id}", method=RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id,Model model) {
	     languageService.deleteLanguage(id);
	     return "redirect:/languages";
	}
}
