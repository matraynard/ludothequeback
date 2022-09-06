package com.example.beans;

import javax.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "customer", schema = "schema1")
@NamedQuery(name = "customer.list", query = "SELECT c FROM Customer c ORDER BY c.id")
public class Customer {

    private static final Logger log = LoggerFactory.getLogger(Customer.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "name")
    private String name;


    public Customer() {
    }

    public Customer(String nom) {
        this.name = nom;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "::: Client :::"
             + "<br>--> Nom : " + this.getName()
             + "<br>---> Id : " + this.getId() + "<br><br>";
    }
}