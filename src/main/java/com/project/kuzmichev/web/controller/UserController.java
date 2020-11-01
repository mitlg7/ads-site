package com.project.kuzmichev.web.controller;

import com.project.kuzmichev.model.domain.user.User;
import com.project.kuzmichev.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id:\\d+}")
    @ResponseBody
    public Optional<User> view(@PathVariable int id ){
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseBody
    public String create(User user){
        userService.createUser(user);
        return "ok";
    }



}
