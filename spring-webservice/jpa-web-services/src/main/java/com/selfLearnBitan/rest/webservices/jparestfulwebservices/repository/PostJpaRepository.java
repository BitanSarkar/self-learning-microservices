package com.selfLearnBitan.rest.webservices.jparestfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.selfLearnBitan.rest.webservices.jparestfulwebservices.entity.Post;
import com.selfLearnBitan.rest.webservices.jparestfulwebservices.entity.User;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Integer>{

}
