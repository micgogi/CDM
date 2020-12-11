package com.example.NewsService.service;

import com.example.NewsService.entity.User;
import com.example.NewsService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

  @Autowired UserRepository userRepository;

  @Autowired BCryptPasswordEncoder encryptor;

  public boolean saveUser(User user) {

    User existingUser = userRepository.findByUserName(user.getUserName());
    if (existingUser == null) {
      user.setPassword(encryptor.encode(user.getPassword()));
      userRepository.save(user);
      return true;

    } else return false;
  }

  public User getUser(String userName, String password) {

    User user = userRepository.findByUserName(userName);

    if (!(user == null) && encryptor.matches(password, user.getPassword())) {
      return user;
    } else return null;
  }
}
