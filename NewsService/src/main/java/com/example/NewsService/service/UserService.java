package com.example.NewsService.service;

import com.example.NewsService.entity.User;

public interface UserService {
	
	boolean saveUser (User user ) ;
	
	User getUser (String userName, String password);

	
}
