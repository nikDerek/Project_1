package com.revature.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

@Table(name = "users")

public class User {
	@Id
	@Column(name="userId")
	private String userId;
	@Column(name="userType")
	private String userType;
	@Column(name="name")
	private String name;
	@Column(name="userName", unique = true)
	private String userName;
	@Column(name="password")
	private String password;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public User(String userId, String userType, String name, String userName, String password) {
		super();
		this.userId = userId;
		this.userType = userType;
		this.name = name;
		this.userName = userName;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, password, userId, userName, userType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(userId, other.userId) && Objects.equals(userName, other.userName)
				&& Objects.equals(userType, other.userType);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userType=" + userType + ", name=" + name + ", userName=" + userName
				+ ", password=" + password + "]";
	}
		
	

}
