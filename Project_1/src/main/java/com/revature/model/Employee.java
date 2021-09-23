package com.revature.model;



import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;





@Entity

@Table(name = "hibernate_employees")

public class Employee {
	
	@Id
	@Column(name="employeeId")
	@GeneratedValue(generator = "employee_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "employee_id_seq", sequenceName = "employee_id_seq")
	private int employeeId;
	
	@Column(name = "name")
	private String employeeName;
	@Column(name = "employeeUserName")
	private String employeeUserName;
	@Column(name = "employeePassword")
	private String employeePassword;
	
	@OneToMany
	private Set<Request> requests;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int employeeId, String employeeName, String employeeUserName, String employeePassword) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeUserName = employeeUserName;
		this.employeePassword = employeePassword;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeUserName() {
		return employeeUserName;
	}

	public void setEmployeeUserName(String employeeUserName) {
		this.employeeUserName = employeeUserName;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeId;
		result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
		result = prime * result + ((employeePassword == null) ? 0 : employeePassword.hashCode());
		result = prime * result + ((employeeUserName == null) ? 0 : employeeUserName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employeeId != other.employeeId)
			return false;
		if (employeeName == null) {
			if (other.employeeName != null)
				return false;
		} else if (!employeeName.equals(other.employeeName))
			return false;
		if (employeePassword == null) {
			if (other.employeePassword != null)
				return false;
		} else if (!employeePassword.equals(other.employeePassword))
			return false;
		if (employeeUserName == null) {
			if (other.employeeUserName != null)
				return false;
		} else if (!employeeUserName.equals(other.employeeUserName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeUserName="
				+ employeeUserName + ", employeePassword=" + employeePassword + "]";
	}
	
	
	
	
	
}