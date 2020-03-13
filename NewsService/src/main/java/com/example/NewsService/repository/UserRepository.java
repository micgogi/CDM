package com.example.NewsService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NewsService.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	 
    User  findByUserName  (String userName); 

}
