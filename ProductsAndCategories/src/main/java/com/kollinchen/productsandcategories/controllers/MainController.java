package com.kollinchen.productsandcategories.controllers;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kollinchen.productsandcategories.models.Category;
import com.kollinchen.productsandcategories.models.Product;
import com.kollinchen.productsandcategories.services.MainService;

@Controller
public class MainController {
	@Autowired
	private MainService mainServ;
	
	@GetMapping("/")
	public String index(
			@ModelAttribute("productObj") Product emptyObj,
			Model model,
			RedirectAttributes redirectAttributes
	) {
		if (model.asMap().containsKey("errors.productObj"))
        {
    		 model.addAttribute("org.springframework.validation.BindingResult.productObj",
    	                model.asMap().get("errors.productObj"));
        }
		return "index.jsp";
	}
	@PostMapping("/product/new")
	public String newProduct(
			@Valid @ModelAttribute("productObj") Product filledObj,
			BindingResult result,
			RedirectAttributes redirectAttributes
	) {
		if (result.hasErrors()) {
        	redirectAttributes.addFlashAttribute("errors.productObj", result);
            redirectAttributes.addFlashAttribute("productObj", filledObj);
        }
		else {
			mainServ.saveProduct(filledObj);
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/category/new")
	public String viewCategory(
			@ModelAttribute("categoryObj") Category emptyObj,
			Model model,
			RedirectAttributes redirectAttributes
	) {
		if (model.asMap().containsKey("errors.categoryObj"))
        {
    		 model.addAttribute("org.springframework.validation.BindingResult.categoryObj",
    	                model.asMap().get("errors.categoryObj"));
        }
		return "category.jsp";
	}
	@PostMapping("/category/new")
	public String newCategory(
			@Valid @ModelAttribute("categoryObj") Category filledObj,
			BindingResult result,
			RedirectAttributes redirectAttributes
	) {
		if (result.hasErrors()) {
        	redirectAttributes.addFlashAttribute("errors.categoryObj", result);
            redirectAttributes.addFlashAttribute("categoryObj", filledObj);
            return "redirect:/category/new";
        }
		else {
			mainServ.saveCategory(filledObj);
			return "redirect:/";
		}
		
		
	}
	
	@GetMapping("/product/{id}")
	public String oneProduct(
			@PathVariable("id") Long prod_id, 
			Model model
	) {
		model.addAttribute("product", mainServ.findProduct(prod_id));
		model.addAttribute("categories", mainServ.allCategories());
		return "viewProduct.jsp";
	}
	@PostMapping("/product/{id}")
	public String addCategoryToProduct(
			@PathVariable("id") Long prod_id,
			@RequestParam("category_id") Long cat_id
	) {
		// USING THE IDS, FIND PRODUCT AND CATEGORY OBJ
		Category oneCat = mainServ.findCategory(cat_id);
		Product oneProd = mainServ.findProduct(prod_id);
		
		// ADD THE RELATIONSHIP TO THE OBJECTS
		oneCat.getProducts().add(oneProd);
//		oneProd.getCategories().add(oneCat);
		
		// SAVE THE NEW INFORMATION IN THE DB
		mainServ.saveCategory(oneCat);
//		mainServ.saveProduct(oneProd);
		
		return "redirect:/product/" + prod_id;
		
	}
	
	@GetMapping("/category/{id}")
	public String oneCategory(
			@PathVariable("id") Long cat_id, 
			Model model
	) {
		model.addAttribute("category", mainServ.findCategory(cat_id));
		model.addAttribute("products", mainServ.allProducts());
		return "viewCategory.jsp";
	}
	@PostMapping("/category/{id}")
	public String addProductToCategory(
			@PathVariable("id") Long cat_id,
			@RequestParam("product_id") Long prod_id
	) {
		// USING THE IDS, FIND PRODUCT AND CATEGORY OBJ
		
		Category oneCat = mainServ.findCategory(cat_id);
		Product oneProd = mainServ.findProduct(prod_id);

		oneProd.getCategories().add(oneCat);
		
		// SAVE THE NEW INFORMATION IN THE DB
		mainServ.saveProduct(oneProd);

		
		return "redirect:/category/" + cat_id;
		
	}
	@RequestMapping(value="/product/{prod_id}/{cat_id}", method=RequestMethod.DELETE)
	    public String deleteProductFromCate(
	    		@PathVariable("prod_id") Long prod_id,
	    		@PathVariable("cat_id") Long cat_id
	    		
	) {
		Category cate = mainServ.findCategory(cat_id);
		Product prob = mainServ.findProduct(prod_id);
		System.out.print(cate.getName());
		System.out.print(prob.getName());
		cate.getProducts().remove(prob);
		prob.getCategories().remove(cate);
		mainServ.saveCategory(cate);
		
	    return "redirect:/category/" + cat_id;
	}
	@RequestMapping(value="/category/{cat_id}/{prod_id}", method=RequestMethod.DELETE)
    public String deleteCategoryFromProd(
    		@PathVariable("prod_id") Long prod_id,
    		@PathVariable("cat_id") Long cat_id
	    		
	) {
		Category cate = mainServ.findCategory(cat_id);
		Product prob = mainServ.findProduct(prod_id);
		cate.getProducts().remove(prob);
		prob.getCategories().remove(cate);
		mainServ.saveProduct(prob);
		
	    return "redirect:/product/" + prod_id;
}
	
}
