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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import webservice.backdata.bean.Message;
import webservice.backdata.services.MessageService;

@RestController
public class MessagesController {

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/message/{mid}", method = RequestMethod.GET)
    public Message getMessageByMid(@PathVariable String mid) {
        return messageService.getMessageByMid(mid);
    }
    
    @RequestMapping(value= "/conversations/{idUser}" , method = RequestMethod.GET)
    public List<Message> getConversation(@PathVariable String idUser){
    	return messageService.getConversation(idUser);
    }
    

	@CrossOrigin
    @RequestMapping(value = "/message/add", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveMessage(@RequestBody JsonNode message) throws HttpException, IOException {
       
		Message messag = messageService.getMessageFromJson(message);
        return messageService.saveMessage(messag);
    }
}
