package com.example.controllers.mapping;

import com.example.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookMappingController {

    private static final Logger log = LoggerFactory.getLogger(BookMappingController.class);

    @Autowired
    private CustomerService customerService;
}