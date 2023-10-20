package org.java.app.db.serv;

import java.util.List;

import org.java.app.db.pojo.Photo;
import org.java.app.db.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
	@Autowired
	private PhotoRepo photoRepo;
	
	
	public void save(Photo photo) {
		photoRepo.save(photo);
	}
	
	public List<Photo> findAll(){
		return photoRepo.findAll();
	}
	
	public Photo findById(int id) {
		return photoRepo.findById(id).get();
	}
	
	public List<Photo> findByTitle(String title){
		return photoRepo.findByTitleContaining(title);
	}
	
	public List<Photo> deleteById(int id){
		return photoRepo.deleteById(id);
	}
}
