package org.java.app.api.controller;

import java.util.List;

import org.java.app.db.pojo.Category;
import org.java.app.db.serv.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/v1.0/categories")
public class CategoryApiController {
	
	
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> getAllCategories(@RequestParam(value = "query", required = false) String name){
	
		List<Category> categories;
		
		if (name != null && !name.isEmpty()) {
	        
			categories = categoryService.findByName(name);
	    } else {
	       
	    	categories = categoryService.findAll();
	    }
		
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Category> getCategoryId(@PathVariable int id){
	
		Category category = categoryService.findById(id);
		 
		 if(category == null) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 }
		
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	

}
