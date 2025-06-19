package com.bootcamp.latihan.repository;

import java.util.ArrayList;
import java.util.List;

import com.bootcamp.latihan.entities.User;

public class UserRepository {
	
	private static List<User> users = new ArrayList<User>();
	static {
		users.add(new User ("faldi", "Admin", "Bandung"));
		users.add(new User ("alia", "Customer", "Jakarta"));
	}
	
	public List<User> getAll(){
		return users;
	}

}
