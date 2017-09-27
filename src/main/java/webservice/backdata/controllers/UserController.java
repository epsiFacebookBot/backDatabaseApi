package webservice.backdata.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webservice.backdata.bean.User;
import webservice.backdata.services.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public User getUserByMid(@PathVariable String id){
		return userService.getUserById(id);
	}
	
	@RequestMapping(value ="/user/add", method = RequestMethod.POST)
	public boolean saveUser(@RequestParam(value = "id",required = true) String id,@RequestParam(value = "name",required = true) String name){
		return userService.saveUser(new User(id,name));
	}

}
