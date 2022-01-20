package com.selfLearnBitan.rest.webservices.jparestfulwebservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.selfLearnBitan.rest.webservices.jparestfulwebservices.dao.PostJpaDaoService;
import com.selfLearnBitan.rest.webservices.jparestfulwebservices.dao.UserDaoService;
import com.selfLearnBitan.rest.webservices.jparestfulwebservices.dao.UserJpaDaoService;
import com.selfLearnBitan.rest.webservices.jparestfulwebservices.entity.Post;
import com.selfLearnBitan.rest.webservices.jparestfulwebservices.entity.User;
import com.selfLearnBitan.rest.webservices.jparestfulwebservices.exception.PostNotFoundException;
import com.selfLearnBitan.rest.webservices.jparestfulwebservices.exception.UserNotFoundException;

@RestController
@RequestMapping(path = "/user-controller/jpa")
public class UserJpaController {
	
	@Autowired
	private UserJpaDaoService uservice;
	@Autowired
	private PostJpaDaoService pservice;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return uservice.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = uservice.findOne(id);
		if(!user.isPresent()) 
			throw new UserNotFoundException("User not found");
		EntityModel<User> model = EntityModel.of(user.get());
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkToUsers.withRel("all-users"));
		return model;
	}
	
	@PostMapping("/users")
	public ResponseEntity createUser(@Valid @RequestBody User user) {
		User savedUser = uservice.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		uservice.deleteById(id);
	}
	
	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveAllPostsById(@PathVariable int id) {
		Optional<User> user = uservice.findOne(id);
		if(!user.isPresent()) 
			throw new UserNotFoundException("User not found");
		List<Post> p = user.get().getPosts();
		if(p==null || p.isEmpty())
			throw new PostNotFoundException("User have no post");
		return p;
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity createPost(@Valid @RequestBody Post post, @PathVariable int id) {
		Optional<User> user = uservice.findOne(id);
		if(!user.isPresent()) 
			throw new UserNotFoundException("User not found");
		post.setUser(user.get());
		
		Post savedPost = pservice.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
}
