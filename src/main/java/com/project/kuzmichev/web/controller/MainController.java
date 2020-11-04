package com.project.kuzmichev.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/ad/create")
    public String createAd(){
        return "createAd";
    }

    @GetMapping("/login")
    public String login(){
        return "form-login";
    }


    @GetMapping("/")
    public String test(){
        return "index";
    }
}
