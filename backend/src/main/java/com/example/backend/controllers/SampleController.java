package com.example.backend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class SampleController {

    @GetMapping("/title")
    public String getTitle() {
        return "<title>Hello from Back-end</title> " +
                "<h1>I'm Nikita</h1>";
    }
}