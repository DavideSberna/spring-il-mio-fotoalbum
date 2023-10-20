package org.java.app.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;



@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PhotoService photoService;
	
	
	@GetMapping("/dashboard/categories")
	public String getCategories(Model model) {
		
		List<Category> categories = categoryService.findAll();
		
		 
		model.addAttribute("categories", categories);
		return "admin/category/read/categories";
	}
	
	@GetMapping("/dashboard/categories/{id}")
	public String getCategorie(@PathVariable("id") int id, Model model) {
		
		Category category = categoryService.findById(id);
		
		 
		model.addAttribute("category", category);
		return "admin/category/show/category";
	}
	
	@GetMapping("/dashboard/categories/create")
	public String createPhoto(Model model) {
		
		
		List<Photo> photos = photoService.findAll();
		
		model.addAttribute("category", new Category());
		model.addAttribute("photos", photos);
		
		return "admin/category/form/create";
	}
	
	@PostMapping("/dashboard/categories/create")
	public String storeCategory(
			
	    @Valid @ModelAttribute("category") Category category, 
	    BindingResult bindingResult, 
	    @RequestParam(value = "photoIds", required = false) List<Integer> photoIds,
	    Model model
	    
	) {
		
		if (bindingResult.hasErrors()) {
			
			List<Photo> photos = photoService.findAll();
			
			model.addAttribute("category", category);
			model.addAttribute("photos", photos);
			
			return "admin/category/form/create";
		}
	     
		List<Photo> photos = new ArrayList<>();
		
		if(photoIds != null) {
			
			for(Integer id : photoIds) {
				Photo currentPhoto = photoService.findById(id);
				
				if(currentPhoto != null) {
					photos.add(currentPhoto);
				}
				category.setPhotos(photos);
				categoryService.save(category);
				
				List<Category> categories = new ArrayList<>();
				
				for(Photo photo : photos) {
					photo.getCategories().add(category);
					photoService.save(photo);
				}
			}
		}
		
	    
	    return "redirect:/dashboard/categories";
	}
	
	@GetMapping("/dashboard/categories/edit/{id}")
	public String editCategory(
	    @PathVariable("id") int id,
	    Model model
	) {
	    Category category = categoryService.findById(id);
	    List<Photo> photos = photoService.findAll();
	    
	    
	    model.addAttribute("category", category);
	    model.addAttribute("photos", photos);
	    
	    return "admin/category/form/edit";
	}
	  
	@PostMapping("/dashboard/categories/edit/{id}")
	public String UpdateCategory(
			@Valid @ModelAttribute("category") Category category,
		    @PathVariable("id") int id,
		    BindingResult bindingResult,
		    Model model
		) {
		
		 
		
		    if (bindingResult.hasErrors()) {
		        List<Photo> photos = photoService.findAll();
		        model.addAttribute("photos", photos);
		        model.addAttribute("category", category);
		        return "admin/category/form/edit";
		    }
		
		System.out.println(category.getId());
	    Category currentCategory = categoryService.findById(id);
	    currentCategory.setName(category.getName());
	    
	    categoryService.save(currentCategory);

	    for (Photo photo : currentCategory.getPhotos()) {
	        photo.getCategories().remove(currentCategory);
	    }

	    if (category.getPhotos() != null) {
	    	
	        for (Photo selectedPhoto : category.getPhotos()) {
	            Photo photo = photoService.findById(selectedPhoto.getId());
	            photo.getCategories().add(currentCategory);
	            photoService.save(selectedPhoto);
	        }
	        
	    }
	    
	    return "redirect:/dashboard/categories";
	}
	
	@PostMapping("/dashboard/categories/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		
		
		Category category = categoryService.findById(id);
		List<Photo> photos = category.getPhotos();
		
		for(Photo photo : photos) {
			photo.getCategories().remove(category);
			photoService.save(photo);
		}
		
		categoryService.deleteById(id);
		
		return "redirect:/dashboard/categories";
	}
	
	
	
	
	
	
	
	
}
