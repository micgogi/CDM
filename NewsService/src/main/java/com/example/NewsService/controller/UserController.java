package com.example.NewsService.controller;

import com.example.NewsService.entity.User;
import com.example.NewsService.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/v1/userservice")
public class UserController {

  @Autowired private UserServiceImpl userService;

  @PostMapping("signup")
  public ResponseEntity<String> saveuser(@RequestBody User user) {

    boolean flag = userService.saveUser(user);
    if (flag) return new ResponseEntity<String>("User Saved", HttpStatus.CREATED);
    else return new ResponseEntity<String>("User Already Exists", HttpStatus.CONFLICT);
  }

  @PostMapping("login")
  public ResponseEntity<?> getUser(@RequestBody User user) {

    User userValid = userService.getUser(user.getUserName(), user.getPassword());

    if (userValid == null) {
      return new ResponseEntity<String>("Invalid User", HttpStatus.UNAUTHORIZED);
    } else return new ResponseEntity<String>("Successfully Logged In", HttpStatus.OK);
  }
}
