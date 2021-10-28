package com.revature.service;

import java.util.List;

import com.revature.model.Request;
import com.revature.repository.RequestRepository;

public class RequestService {
	
	private RequestRepository requestRepository;
	
	public RequestService() {
		this.requestRepository = new RequestRepository();
	}
	
	public List<Request> findAllRequests(){
		List<Request> allRequests = this.requestRepository.findAllReqs();
		
		return allRequests;		
	}
	
	public List<Request> findAllReqs(){
		
		return this.requestRepository.findAllReqs();
	}
	
	public void save(Request request) {
		this.requestRepository.saveRequest(request);
	}
	
	public List<Request> requestsByUserName(String userName) {
		return this.requestRepository.requestsByUserName(userName);
	}
	
	public void approve(int requestId) {
		this.requestRepository.approve(requestId);
	}
	
	public void deny(int requestId) {
		this.requestRepository.deny(requestId);
	}

}
