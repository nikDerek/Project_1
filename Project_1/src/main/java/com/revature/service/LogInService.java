package com.revature.service;

import com.revature.repository.LogInRepository;

public class LogInService {
	
	private LogInRepository logInRepository;

	public Object logIn(String userName, String password) {
		return this.logInRepository.logIn(userName, password);
	}
}
