package webservice.backdata.persistence;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import webservice.backdata.bean.User;

@Repository
@Configuration
@PropertySource("classpath:/mongoConfig.properties")
public class UserPersistence extends AbstractPersistence {

    public boolean saveUser(User user) {
        if (user != null) {
            mongoTemplate.save(user);
            return true;
        }
        return false;
    }

    public User getUserById(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), User.class);

    }

	public List<User> findUsers() {
		return mongoTemplate.findAll(User.class);
	}

}
