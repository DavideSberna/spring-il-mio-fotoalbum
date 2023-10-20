package org.java.app.controller;

import java.util.ArrayList;
import java.util.List;


import org.java.app.db.pojo.Photo;
import org.java.app.db.pojo.Category;
import org.java.app.db.serv.CategoryService;
import org.java.app.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {

	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	
	@GetMapping("/dashboard")
	public String getAll(
			
			@RequestParam(name = "title", required = false) String title,
			Model model
			
			) {
		
		List<Photo> photos = new ArrayList<>();
		List<Category> categories = new ArrayList<>();
		
		if(title != null) {
			photos = photoService.findByTitle(title);
		} else {
			photos = photoService.findAll();
		}
		
		categories = categoryService.findAll();
		
		model.addAttribute("photos", photos);
		model.addAttribute("categories", categories);
		
		
		return "admin/dashboard/read/dashboard";
	}
}
