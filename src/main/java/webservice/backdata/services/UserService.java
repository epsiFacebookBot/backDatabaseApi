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
        checkUser(user);
        return userPersistence.saveUser(user);

    }

    public User getUserById(String id) {

        return userPersistence.getUserById(id);
    }

    private boolean checkUser(User user) {
        String error = " errors found : \n";
        int errorCount = 0;
        if (user == null) {
            errorCount++;
            error += "User\n";
        } else {
            if (user.getId() == null || user.getId().equals("")) {
                errorCount++;
                error += "Id\n";
            }
            if (user.getName() == null || user.getName().equals("")) {
                errorCount++;
                error += "Name\n";
            }
        }

        error = errorCount + error;
        if (errorCount > 0) {
            throw new IllegalArgumentException(error);
        }
        return true;
    }


}
