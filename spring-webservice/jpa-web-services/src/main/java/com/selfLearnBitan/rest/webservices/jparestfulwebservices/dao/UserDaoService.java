package com.selfLearnBitan.rest.webservices.jparestfulwebservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.selfLearnBitan.rest.webservices.jparestfulwebservices.entity.User;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<User>();
	
	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Harry", new Date()));
		users.add(new User(4, "Bitan", new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(users.size()+1);
		}
		else {
			users.remove(user.getId()-1);
		}
		users.add(user.getId()-1,user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user:users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteById(int id) {
		Iterator<User> itr = users.iterator();
		while(itr.hasNext()) {
			User usr = itr.next();
			if(usr.getId() == id) {
				itr.remove();
				return usr;
			}
		}
		return null;
	}
	
}
