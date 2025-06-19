package com.bootcamp.latihan.entities;

public class User {
	
	private String userName;
	private String role;
	private String address;
	
	public User() {
		
		
	}
	
	public User(String userName, String role, String address) {
		this.userName = userName;
		this.role = role;
		this.address = address;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
