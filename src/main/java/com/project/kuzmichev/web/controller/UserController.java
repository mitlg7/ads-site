package com.project.kuzmichev.web.controller;

import com.project.kuzmichev.model.domain.user.User;
import com.project.kuzmichev.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id:\\d+}")
    @ResponseBody
    public Optional<User> view(@PathVariable int id ){
        return userService.getUserById(id);
    }


}
