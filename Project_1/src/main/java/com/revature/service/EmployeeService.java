package com.revature.service;

import java.util.List;

import com.revature.model.Employee;
import com.revature.repository.EmployeeRepository;

public class EmployeeService {

private EmployeeRepository employeeRepository;
	
	public EmployeeService() {
		this.employeeRepository = new EmployeeRepository();
	}
	public List<Employee> findAllEmployees(){
		List<Employee> allEmployees = this.employeeRepository.findAll();
		
		return allEmployees;
	}
	public List<Employee> findAll(){
		
		return this.employeeRepository.findAll();
	}
	public void save(Employee employee) {
		this.employeeRepository.save(employee);
	}
	public Employee getByUsername(String username) {
		return this.employeeRepository.getByUsername(username);
	}
}

