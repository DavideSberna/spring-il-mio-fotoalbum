package org.java.app.api.controller;

 
import java.util.List;

import org.java.app.db.pojo.Photo; 
import org.java.app.db.serv.CategoryService;
import org.java.app.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/v1.0/photos")
public class PhotoApiController {
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Photo>> getAllPizzas(@RequestParam(value = "query", required = false) String name){
	
		List<Photo> photos;
		
		if (name != null && !name.isEmpty()) {
	        
			photos = photoService.findByTitle(name);
	    } else {
	       
	    	photos = photoService.findAll();
	    }
		
		return new ResponseEntity<>(photos, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Photo> getPizzaId(@PathVariable int id){
	
		Photo photo = photoService.findById(id);
		 
		 if(photo == null) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 }
		
		return new ResponseEntity<>(photo, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Photo> createPizza(@RequestBody Photo photo){
	
		  photoService.save(photo);
		  
		
		return new ResponseEntity<>(photo, HttpStatus.OK);
	}

}
