package com.example.entity;

import javax.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@NamedQuery(name = "customer.list", query = "SELECT c FROM Customer c ORDER BY c.id")
public class Customer {

    private static final Logger log = LoggerFactory.getLogger(Customer.class);
    // ------------- fields -------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // ------------- relationships -------------
    @OneToMany/*(cascade=ALL, mappedBy="customer")*/
    public List<Purchase> purchases;

    // ------------- constructors -------------
    public Customer() {
    }

    public Customer(String nom) {
        this.name = nom;
    }

    // ------------- getters -------------
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // ------------- setters -------------
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    // ------------- others -------------
    @Override
    public String toString(){
        return "::: Client :::"
             + "<br>--> Nom : " + this.getName()
             + "<br>---> Id : " + this.getId() + "<br><br>";
    }
}