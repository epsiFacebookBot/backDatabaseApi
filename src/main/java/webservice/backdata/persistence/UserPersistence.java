package webservice.backdata.persistence;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import webservice.backdata.bean.User;

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

}
