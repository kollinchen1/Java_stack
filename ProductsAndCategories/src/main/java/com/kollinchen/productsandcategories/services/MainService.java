package com.kollinchen.productsandcategories.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kollinchen.productsandcategories.models.Category;
import com.kollinchen.productsandcategories.models.Product;
import com.kollinchen.productsandcategories.repositories.CategoryRepository;
import com.kollinchen.productsandcategories.repositories.ProductRepository;

@Service
public class MainService {

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository cateRepo;
	
	//product
	public List<Product> allProducts(){
		return productRepo.findAll();
	}
	
	public Product saveProduct(Product p) {
		return productRepo.save(p);
	}
	public Product findProduct(Long prod_id) {
		return productRepo.findById(prod_id).orElse(null);
	}
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}
	
	//category
	public List<Category> allCategories(){
		return cateRepo.findAll();
	}
	
	public Category saveCategory(Category c) {
		return cateRepo.save(c);
	}

	public Category findCategory(Long cat_id) {
		return cateRepo.findById(cat_id).orElse(null);
	}
	public void deleteCategory(Long id) {
//		System.out.println(findCategory(id).getProducts());
		
		//cateRepo.deleteById(id);
	 }
}
