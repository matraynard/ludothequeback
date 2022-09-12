package com.example.services;

import com.example.entity.Customer;
import com.example.repository.ICustomerJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    ICustomerJpaRepository repository;

    @Transactional
    public Customer add(String name) {
        return name != null
                ? add(new Customer(name))
                : null;
    }

    @Transactional
    public Customer add(Customer customer) {
        if (customer != null){
            repository.save(customer);
            return customer;
        }
        return null;
    }

    @Transactional
    public Customer delete(Long id) {
        return delete(findById(id));
    }

    @Transactional
    public Customer delete(Customer customer) {
        if (customer != null){
            repository.delete(customer);
            return customer;
        }
        return null;
    }

    public List<Customer> findAll() {
        return  repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Customer findById(Long id) {
        return repository.findById(id).get();
    }

    public List<Customer> findByName(String name) {
        if (name != null){
            List<Customer> foundCustomers = repository.findByName(name);
            foundCustomers.sort(Comparator.comparing(Customer::getName));
            return foundCustomers;
        }
        return null;
    }

    @Transactional
    public Customer update(Long id, String newName) {
        Customer customer = findById(id);
        if (newName != null && customer != null){
            customer.setName(newName);
            return update(customer);
        }
        return customer;
    }

    @Transactional
    public Customer update(Customer customer) {
        repository.save(customer);
        return customer;
    }
}