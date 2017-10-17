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
        if (mid == null || mid.equals("")) {
            throw new IllegalArgumentException("Invalid Mid");
        }

        return messagePersistence.getMessageByMid(mid);
    }

    public boolean saveMessage(Message message) {
        checkMessage(message);
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
