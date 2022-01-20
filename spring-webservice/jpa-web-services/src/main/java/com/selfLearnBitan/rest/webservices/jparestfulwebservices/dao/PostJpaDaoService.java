package com.selfLearnBitan.rest.webservices.jparestfulwebservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.selfLearnBitan.rest.webservices.jparestfulwebservices.entity.Post;
import com.selfLearnBitan.rest.webservices.jparestfulwebservices.entity.User;
import com.selfLearnBitan.rest.webservices.jparestfulwebservices.repository.PostJpaRepository;
import com.selfLearnBitan.rest.webservices.jparestfulwebservices.repository.UserJpaRepository;

@Component
public class PostJpaDaoService{
	
	@Autowired
	private PostJpaRepository postJpaRepository;
	
	
	public List<Post> findAll() {
		return postJpaRepository.findAll();
	}
	
	public Post save(Post post) {
		return postJpaRepository.save(post);
		
	}
	
	public Optional<Post> findOne(int id) {
		return postJpaRepository.findById(id);
	}
	
	public void deleteById(int id) {
		postJpaRepository.deleteById(id);
	}
	
}
