package webservice.backdata.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import webservice.backdata.bean.User;
import webservice.backdata.services.UserService;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    
    @CrossOrigin
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUserByMid(@PathVariable String id) {
        return userService.getUserById(id);
    }
    @CrossOrigin
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveUser(@RequestBody JsonNode userJson) {
        return userService.saveUser(UserService.getUserFromJson(userJson));
    }

}
