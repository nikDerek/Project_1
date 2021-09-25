package com.revature.service;

import java.util.List;

import com.revature.model.User;
import com.revature.repository.UserRepository;

public class UserService {

private UserRepository userRepository;
	
	public UserService() {
		this.userRepository = new UserRepository();
	}
	
	public List<User> findAllUsers(){
		List<User> allUsers = this.userRepository.findAll();
		
		return allUsers;
	}
	
	public List<User> findAll(){
		
		return this.userRepository.findAll();
	}
	public void save(User user) {
		this.userRepository.save(user);
	}
	public Object findById(String pathParam) {
		
		return this.userRepository.findUserById(pathParam);
	}
}
