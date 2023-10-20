package org.java.app.db.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Il titolo non può essere vuoto.")
    @Pattern(regexp = "^(?!\\s*$).+", message = "Il titolo non può essere formato solo da spazi vuoti.")
    private String title;

    @NotBlank(message = "La descrizione non può essere vuota.")
    @Pattern(regexp = "^(?!\\s*$).+", message = "La descrizione non può essere formato solo da spazi vuoti.")
    private String description;

    @NotBlank(message = "L'URL non può essere vuoto.")
    private String url;

    private boolean visible;
    
    @ManyToMany
    @JsonBackReference
    private List<Category> categories = new ArrayList<>();
    
     
	public Photo() {}
    public Photo(String title,String description,String url, boolean visible, Category... categories ) {
    	setTitle(title);
    	setDescription(description);
    	setUrl(url);
    	setVisible(visible);
    	getCategories().addAll(Arrays.asList(categories));
    }
    
    public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean getVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public List<Category> getCategories() {
		return categories;
	}
	
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	 
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + getId() + "] \n" +
				"Titolo: " + getTitle() + "\n" +
				"Descrizione" + getDescription() + "\n" +
				"visibile" + getVisible();
				
	}
    
    
    
    
    
    
    
}
