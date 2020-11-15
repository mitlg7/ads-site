package com.project.kuzmichev.web.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;


//todo
@RestController
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
}
