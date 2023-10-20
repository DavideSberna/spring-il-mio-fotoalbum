package org.java.app.db.repo;

import org.java.app.db.pojo.email.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Integer> {

	
}
