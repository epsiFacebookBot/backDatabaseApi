package webservice.backdata.services;

import org.springframework.beans.factory.annotation.Autowired;

import webservice.backdata.bean.Message;
import webservice.backdata.persistence.MessagePersistence;

public class MessageService {
	@Autowired
	MessagePersistence messagePersistence;
	

	public Message getMessageByMid(String mid) {
		return messagePersistence.getMessageByMid(mid);
	}
	
	public boolean saveMessage(Message message){
		return messagePersistence.saveMessage(message);
	}


}
