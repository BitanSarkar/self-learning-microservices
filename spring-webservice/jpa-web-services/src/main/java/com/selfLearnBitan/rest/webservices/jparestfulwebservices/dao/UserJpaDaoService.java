package com.selfLearnBitan.rest.webservices.jparestfulwebservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.selfLearnBitan.rest.webservices.jparestfulwebservices.entity.User;
import com.selfLearnBitan.rest.webservices.jparestfulwebservices.repository.UserJpaRepository;

@Component
public class UserJpaDaoService{
	
	@Autowired
	private UserJpaRepository userJpaRepository;
	
	
	public List<User> findAll() {
		return userJpaRepository.findAll();
	}
	
	public User save(User user) {
		return userJpaRepository.save(user);
		
	}
	
	public Optional<User> findOne(int id) {
		return userJpaRepository.findById(id);
	}
	
	public void deleteById(int id) {
		userJpaRepository.deleteById(id);
	}
	
}
