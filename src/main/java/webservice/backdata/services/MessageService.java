package webservice.backdata.services;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;

import webservice.backdata.bean.Message;
import webservice.backdata.persistence.MessagePersistence;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

@Service
public class MessageService {
    @Autowired
    MessagePersistence messagePersistence;
    private HttpClient client;

	@PostConstruct
	private void init() {

		client = new HttpClient();
		client.getParams().setParameter("http.useragent", "Test Client");

	}

    public Message getMessageByMid(String mid) {
        if (mid == null || mid.equals("")) {
            throw new IllegalArgumentException("Invalid Mid");
        }

        return messagePersistence.getMessageByMid(mid);
    }
    
    public static Message getMessageFromJson(JsonNode json){
    	return new Gson().fromJson(json.toString(),Message.class);
    }

    public boolean saveMessage(Message message) throws HttpException, IOException {
        checkMessage(message);

        StringRequestEntity requestEntity = new StringRequestEntity(
        	    new Gson().toJson(message),
        	    "application/json",
        	    "UTF-8");
        String token = "EAAWhdVgZA0nwBALALwNXQTk6vKsWsHJmH5PR9JjS7yuN4oWbYSE2auLJisSZBWELP9G7ZAUWk70XPPywyOqZCj2KMOiTOZCDNAjKZBEV01EpUc2joHVPMREmAUqPrQ4TFwyIleQmSZCov7kJsXYTlqJuUJUQDuWut3vIiW2fkMloAZDZD";
        PostMethod post = new PostMethod("https://graph.facebook.com/v2.6/me/messages");		
		post.setRequestHeader("access_token", token);
		post.setRequestEntity(requestEntity);
		client.executeMethod(post);
        return messagePersistence.saveMessage(message);
    }

    private boolean checkMessage(Message message) {
        String error = " errors found : \n";
        int errorCount = 0;
        if (message == null) {
            errorCount++;
            error += "Message\n";
        } else {
            if (message.getMid() == null || message.getMid().equals("")) {
                errorCount++;
                error += "Mid\n";
            }
            if (message.getText() == null || message.getText().equals("")) {
                errorCount++;
                error += "Text\n";
            }
            if (message.getIdFrom() == null || message.getIdFrom().equals("")) {
                errorCount++;
                error += "IdFrom\n";
            }
            if (message.getIdTo() == null || message.getIdTo().equals("")) {
                errorCount++;
                error += "IdTo\n";
            }
        }
        error = errorCount + error;

        if (errorCount > 0) {
            throw new IllegalArgumentException(error);
        }
        return true;
    }

	public List<Message> getConversation(String idUser) {
		if(idUser == null || idUser == ""){
			throw new IllegalArgumentException("idUser is nul or empty");
		}else{
			return messagePersistence.getConversation(idUser);
		}
	}


}
