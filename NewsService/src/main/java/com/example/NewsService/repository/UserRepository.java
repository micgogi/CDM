package com.example.NewsService.repository;

import com.example.NewsService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  User findByUserName(String userName);
}
