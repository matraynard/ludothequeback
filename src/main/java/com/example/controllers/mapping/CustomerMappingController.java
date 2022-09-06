package com.example.controllers.mapping;

import com.example.beans.Customer;
import com.example.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerMappingController {

    private static final Logger log = LoggerFactory.getLogger(CustomerMappingController.class);
    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public String hello() {
        return "hello; world";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public List<Customer> list() {
        return customerService.list();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getOne/{id}")
    public String findById(@PathVariable Long id) {
        return customerService.findById(id).toString();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAll/{name}")
    public String findCustomersByName(@PathVariable String name) {
        return customerService.findByName(name).toString();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add/{name}")
    public String add(@PathVariable String name) {
        customerService.add(name);
        return "Le client " + name + " a été ajouté.";
    }
}