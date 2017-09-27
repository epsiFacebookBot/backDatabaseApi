package webservice.backdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.backdata.bean.Message;
import webservice.backdata.persistence.MessagePersistence;


@Service
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
