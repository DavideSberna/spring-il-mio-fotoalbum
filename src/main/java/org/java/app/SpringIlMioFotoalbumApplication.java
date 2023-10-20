package org.java.app;

import java.util.Arrays;

import org.java.app.db.auth.pojo.Role;
import org.java.app.db.auth.pojo.User;
import org.java.app.db.auth.service.RoleService;
import org.java.app.db.auth.service.UserService;
import org.java.app.db.pojo.Category;
import org.java.app.db.pojo.Photo;
import org.java.app.db.pojo.email.Message;
import org.java.app.db.serv.CategoryService;
import org.java.app.db.serv.MessageService;
import org.java.app.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {
	
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MessageService messageService;

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		 	Category c1 = new Category("cat 1");
		    Category c2 = new Category("cat 2");
		    Category c3 = new Category("cat 3");
		    
		    categoryService.save(c1);
		    categoryService.save(c2);
		    categoryService.save(c3);

		    Photo p1 = new Photo("photo 1", "descrizione 1 ", "https://www.touringclub.it/sites/default/files/contest_item_image/dsc_0643.jpg", true);
		    Photo p2 = new Photo("photo 2", "descrizione 1 ", "https://www.touringclub.it/sites/default/files/contest_item_image/dsc_0643.jpg", true);
		    Photo p3 = new Photo("photo 3", "descrizione 1 ", "https://www.touringclub.it/sites/default/files/contest_item_image/dsc_0643.jpg", true);
		    Photo p4 = new Photo("photo 4", "descrizione 1 ", "https://www.touringclub.it/sites/default/files/contest_item_image/dsc_0643.jpg", true);
		    
		     
		    //c1.getPhotos().addAll(Arrays.asList(p1, p3));
		    //c2.getPhotos().addAll(Arrays.asList(p1, p4));
		    //c3.getPhotos().addAll(Arrays.asList(p2, p3, p4));

		    
		    p1.getCategories().addAll(Arrays.asList(c1, c2));
		    p2.getCategories().addAll(Arrays.asList(c3));
		    p3.getCategories().addAll(Arrays.asList(c1, c3));
		    p4.getCategories().addAll(Arrays.asList(c2, c3));
		    
		    
		    

		   
		    photoService.save(p1);
		    photoService.save(p2);
		    photoService.save(p3);
		    photoService.save(p4);
		    
		    
		    
		    Role userRole = new Role("USER");
			Role adminRole = new Role("ADMIN");
			
			roleService.save(userRole);
			roleService.save(adminRole);
			
			final String pswAdmin = new BCryptPasswordEncoder().encode("1234");
			final String pswUser = new BCryptPasswordEncoder().encode("1234");
			
			User admin = new User("Davide", pswAdmin, adminRole);
			User user = new User("Gio", pswUser, userRole);
			
			userService.save(admin);
			userService.save(user);
			
			Message mes1 = new Message("email@gmail.com", "Marco", "primo messaggio email", user);
			
			messageService.save(mes1);
		
	}

}
