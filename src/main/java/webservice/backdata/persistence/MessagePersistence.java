package webservice.backdata.persistence;

import java.util.List;

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
    
    public List<Message> getConversation(String idUser){
    	Query query = new Query();    
    	Criteria criteria = new Criteria();
    	criteria.orOperator(Criteria.where("idFrom").is("idUser") ,Criteria.where("idTo").is("idUser"));
    	query.addCriteria(criteria);
    	return mongoTemplate.find(query, Message.class);
    }

    public boolean saveMessage(Message message) {
        if (message != null) {
            mongoTemplate.save(message);
            return true;
        }
        return false;
    }

}
