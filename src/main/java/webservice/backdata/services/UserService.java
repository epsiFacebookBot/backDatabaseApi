package webservice.backdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webservice.backdata.bean.User;
import webservice.backdata.persistence.UserPersistence;

@Service
public class UserService {
	@Autowired
	UserPersistence userPersistence;

	public boolean saveUser(User user) {
		return userPersistence.saveUser(user);
		
	}

	public User getUserById(String id) {
		return userPersistence.getUserById(id);
	}
	
	

}
