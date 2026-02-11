package com.spring_boot.app.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

        @GetMapping("/user")
        public String getUser() {
            return "Welcome to User Controller";
        }


    }

