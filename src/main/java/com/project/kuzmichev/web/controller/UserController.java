package com.project.kuzmichev.web.controller;

import com.project.kuzmichev.model.domain.ad.Ad;
import com.project.kuzmichev.model.domain.user.User;
import com.project.kuzmichev.service.ad.AdService;
import com.project.kuzmichev.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AdService adService;

    @GetMapping("/{username}")
    @ResponseBody
    public User view(@PathVariable String username ){
        return userService.getUserByUsername(username);
    }

    @GetMapping("/{username}/ads")
    @ResponseBody
    public List<Ad> adsByUser(@PathVariable String username){
        return adService.getAllAdsByUsername(username);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity putUser(@Valid @RequestBody User user, BindingResult bindingResult){ //FIXME адекватный put
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        User own = userService.getUserByUsername(user.getUsername());
        System.out.println(user.toString());
        own.setBirthday(user.getBirthday());
        own.setEmail(user.getEmail());
        own.setFirstName(user.getFirstName());
        own.setSecondName(user.getSecondName());
        own.setPatronymic(user.getPatronymic());
        own.setPhone(user.getPhone());
        userService.save(own);
        return ResponseEntity.ok().body("ok");
    }
}
