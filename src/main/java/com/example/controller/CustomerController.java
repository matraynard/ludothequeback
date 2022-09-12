package com.example.controller;

import com.example.entity.Customer;
import com.example.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable Long id){
        Customer customer = customerService.delete(id);
        return "Le client " + customer.getName() + "a été supprimé.";
    }

    @GetMapping(path = "/list")
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Customer findById(@PathVariable Long id){
        return customerService.findById(id);
    }

    @GetMapping(path = "/byname/{name}")
    public String findByName(@PathVariable String name) {
        return customerService.findByName(name).toString();
    }

    @GetMapping(path = "/hello")
    public String hello(){
        return "C'est la page de hello world. Retourne travailler maintenant.";
    }

    @PostMapping(path = "/{name}")
    public Customer save(@PathVariable String name){
        Customer customer = new Customer(name);
        customerService.add(customer);
        return customer;
    }

    @PutMapping(path = "/{id}/{newName}")
    public Customer update(@PathVariable Long id, @PathVariable String newName){
        return customerService.update(id, newName);
    }
}