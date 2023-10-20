package org.java.app.api.controller;

import org.java.app.db.pojo.email.Message;
import org.java.app.db.serv.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/v1.0/message")
public class MessageApiController {
	
	@Autowired
	private MessageService messageService;
	
	

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@Valid @RequestBody Message message) {
        messageService.save(message);
        return ResponseEntity.ok("Messaggio inviato e utente creato con successo.");
    }
	
}
