package webservice.backdata.persistence;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import webservice.backdata.bean.Message;

@Repository
@Configuration
@PropertySource("classpath:/mongoConfig.properties")
public class MessagePersistence extends AbstractPersistence {


    public Message getMessageByMid(String mid) {
        return mongoTemplate.findOne(new Query(Criteria.where("mid").is(mid)), Message.class);
    }

    public boolean saveMessage(Message message) {
        if (message != null) {
            mongoTemplate.save(message);
            return true;
        }
        return false;
    }

}
