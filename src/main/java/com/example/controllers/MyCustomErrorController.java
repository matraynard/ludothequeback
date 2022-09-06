package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;

public class MyCustomErrorController implements ErrorController {

    private static final Logger log = LoggerFactory.getLogger(MyCustomErrorController.class);

    private static final String PATH = "/error";

    @GetMapping(value=PATH)
    public String error() {
        return "Error haven";
    }
}
