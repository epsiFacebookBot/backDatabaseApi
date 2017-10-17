package webservice.backdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import webservice.backdata.bean.Message;
import webservice.backdata.persistence.MessagePersistence;

import java.util.HashMap;

@Service
public class MessageService {
    @Autowired
    MessagePersistence messagePersistence;

    public Message getMessageByMid(String mid) {
        if (mid == null || mid.equals("")) {
            throw new IllegalArgumentException("Invalid Mid");
        }

        return messagePersistence.getMessageByMid(mid);
    }

    public boolean saveMessage(Message message) {
        checkMessage(message);

        // Add the message to the facebook discussion
        RestTemplate restTemplate = new RestTemplate();
        String token = "EAAWhdVgZA0nwBALALwNXQTk6vKsWsHJmH5PR9JjS7yuN4oWbYSE2auLJisSZBWELP9G7ZAUWk70XPPywyOqZCj2KMOiTOZCDNAjKZBEV01EpUc2joHVPMREmAUqPrQ4TFwyIleQmSZCov7kJsXYTlqJuUJUQDuWut3vIiW2fkMloAZDZD";
        HashMap<String, String> vars = new HashMap<>();
        vars.put("access_token", token);
        Message messageFromFacebook = restTemplate.postForObject("https://graph.facebook.com/v2.6/me/messages", message, Message.class, vars);

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

}
