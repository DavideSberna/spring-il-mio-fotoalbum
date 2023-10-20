package org.java.app.db.serv;

import java.util.List;

import org.java.app.db.pojo.Category;
import org.java.app.db.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	
	public void save(Category category) {
		categoryRepo.save(category);
	}
	
	public List<Category> findAll(){
		return categoryRepo.findAll();
	}
	
	public Category findById(int id) {
		return categoryRepo.findById(id).get();
	}
	
	public List<Category> findByName(String name){
		return categoryRepo.findByNameContaining(name);
	}
	
	public List<Category> deleteById(int id){
		return categoryRepo.deleteById(id);
	}
	
	
}
