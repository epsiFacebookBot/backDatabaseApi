package webservice.backdata.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import webservice.backdata.bean.Message;
import webservice.backdata.services.MessageService;

@RestController
public class MessagesController {

    @Autowired
    MessageService messageService;
    
    @CrossOrigin
    @RequestMapping(value = "/message/{mid}", method = RequestMethod.GET)
    public Message getMessageByMid(@PathVariable String mid) {
        return messageService.getMessageByMid(mid);
    }
    @CrossOrigin
    @RequestMapping(value= "/conversations/{idUser}" , method = RequestMethod.GET)
    public List<Message> getConversation(@PathVariable String idUser){
    	return messageService.getConversation(idUser);
    }
    
    @CrossOrigin
    @RequestMapping(value= "/messageCount" , method = RequestMethod.GET)
    public long getMessageCount(){
    	return messageService.getMessageCount();
    }
    
	@CrossOrigin
    @RequestMapping(value = "/message/add", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveMessage(@RequestBody JsonNode message) throws HttpException, IOException {
       
		Message messag = MessageService.getMessageFromJson(message);
        return messageService.saveMessage(messag);
    }
}
