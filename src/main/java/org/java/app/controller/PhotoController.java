package org.java.app.controller;

import java.util.List;

import org.java.app.db.pojo.Category;
import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.CategoryService;
import org.java.app.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class PhotoController {

	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	 
	
	
	@GetMapping("/dashboard/photos")
	public String getPhotos(Model model) {
		
		List<Photo> photos = photoService.findAll();
		model.addAttribute("photos", photos);
		
		return "admin/photo/read/photos";
	}
	
	@GetMapping("/dashboard/photos/{id}")
	public String getPhoto(@PathVariable("id") int id, Model model) {
		
		Photo photo = photoService.findById(id);
		
		
		model.addAttribute("photo", photo);
		
		
		return "admin/photo/show/photo";
	}
	
	@GetMapping("/dashboard/photos/create")
	public String createPhoto(Model model) {
		
		
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("photo", new Photo());
		model.addAttribute("categories", categories);
		
		return "admin/photo/form/create";
	}
	
	@PostMapping("/dashboard/photos/create")
	public String storePhoto(
			
			@Valid @ModelAttribute("photo") Photo photo, 
			BindingResult bindingResult, 
			Model model
			
			) {
		
		if (bindingResult.hasErrors()) {
			
	        List<Category> categories = categoryService.findAll();
	        
	        model.addAttribute("photo", photo);
	        model.addAttribute("categories", categories);
	        
	        return "admin/photo/form/create";
	    }
		
		photoService.save(photo);
		
		return "redirect:/dashboard/photos/" + photo.getId();
	}
	
	@GetMapping("/dashboard/photos/edit/{id}")
	public String editPhoto(
			
			@PathVariable("id") int id,
			Model model
			
			) {
		
		Photo photo = photoService.findById(id);
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("photo", photo);
		model.addAttribute("categories", categories);
		
		
		
		
		return "admin/photo/form/edit";
	}
	
	@PostMapping("/dashboard/photos/edit/{id}")
	public String updatePhoto(
			
			@Valid @ModelAttribute("photo") Photo photo,
	        @PathVariable("id") int id,
	        BindingResult bindingResult,
	        Model model
			
			) {
		
		 if (bindingResult.hasErrors()) {
		        List<Category> categories = categoryService.findAll();
		        
		        model.addAttribute("categories", categories);
		        
		        
		        model.addAttribute("photo", photo);
		        System.out.println("ccc");
		        return "admin/photo/form/edit";
		    }
		
		Photo currentPhoto = photoService.findById(id);
		
		currentPhoto.setTitle(photo.getTitle());
		currentPhoto.setDescription(photo.getDescription());
		currentPhoto.setUrl(photo.getUrl());
		currentPhoto.setVisible(photo.getVisible());
		currentPhoto.setCategories(photo.getCategories());
		
		photoService.save(currentPhoto);
		
		
		
		return "redirect:/dashboard/photos/" + id;
	}
	
	@PostMapping("/dashboard/photos/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		
		photoService.deleteById(id);
		
		return "redirect:/dashboard/photos";
	}
	

	
	
	
}
