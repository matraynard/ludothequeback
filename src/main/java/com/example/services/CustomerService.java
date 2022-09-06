package com.example.services;

import com.example.beans.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

@Service
public class CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    @PersistenceContext
    EntityManager entityManager;

    public List<Customer> list() {
        TypedQuery<Customer> query = entityManager.createNamedQuery("customer.list", Customer.class);
        return query.getResultList();
    }

    @Transactional
    public Customer findById(Long id) {
        /*Query q = entityManager.createQuery("FROM customer c WHERE c.id = :id");
        q.setParameter("id", id);
        return (customer)q.getSingleResult();*/
        Customer response = (Customer) entityManager.find(Customer.class, id);
        return response;
    }

    public List<Customer> findByName(String name) {
        TypedQuery<Customer> response = entityManager.createQuery("SELECT c FROM Customer c WHERE c.nom LIKE '%" + name + "%'", Customer.class);
        return response.getResultList();
    }

    public void add(String name) {
        Customer c = new Customer(name);
        entityManager.persist(c);
    }
}