package org.java.app.db.serv;

import org.java.app.db.pojo.email.Message;
import org.java.app.db.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MessageService {
	
    @Autowired
    private MessageRepo messageRepo;

    public void save(Message message) {
        messageRepo.save(message);
    }
}