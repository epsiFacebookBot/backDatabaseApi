package webservice.backdata.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webservice.backdata.bean.Message;
import webservice.backdata.services.MessageService;

@RestController
public class MessagesController {

	@Autowired
	MessageService messageService;
	
	@RequestMapping(value = "/message/{mid}", method = RequestMethod.GET)
	public Message getMessageByMid(@PathVariable String mid){
		return messageService.getMessageByMid(mid);
	}
	
	@RequestMapping(value ="/message/add", method = RequestMethod.POST)
	public boolean saveMessage(@RequestParam Message message){
		return messageService.saveMessage(message);
	}
}
