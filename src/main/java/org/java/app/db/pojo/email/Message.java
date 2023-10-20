package org.java.app.db.pojo.email;

import org.java.app.db.auth.pojo.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    
    @Column(nullable = false)
    @NotBlank(message = "L'email non può essere vuota. Inserisci un valore valido.")
    @Email(message = "L'email non è valida. Inserisci un valore valido.")
    private String email;
    
    @Column(nullable = false)
    @NotBlank(message = "Il nome non può essere vuoto. Inserisci un valore valido.")
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Il messaggio non può essere vuoto. Inserisci un valore valido.")
    private String message;

    @ManyToOne
    private User user;
    
    public Message() {}
    public Message(String email,String name, String message, User user) {
    	setEmail(email);
    	setName(name);
    	setMessage(message);
    	setUser(user);
    }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    
}
