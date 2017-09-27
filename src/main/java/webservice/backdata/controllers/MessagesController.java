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
	public boolean saveMessage(@RequestParam(value = "mid",required = true) String mid,@RequestParam(value = "text",required = true) String text,@RequestParam(value = "idFrom",required = true)String idFrom ,@RequestParam(value = "idTo",required = true) String idTo , @RequestParam(value = "attachmentType",required = false)String attachmentType , @RequestParam(value = "payload",required = false) String payload){
		Message message = new Message(mid,text,idFrom,idTo);
		if(attachmentType != null){
			message.setAttachmentType(attachmentType);
		}
		if(payload != null){
			message.setPayload(payload);
		}
		return messageService.saveMessage(message);
	}
}
