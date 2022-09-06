package com.example.beans;

import javax.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "customer", schema = "schema1")
@NamedQuery(name = "customer.list", query = "SELECT c FROM Customer c ORDER BY c.id")
public class Customer {

    private static final Logger log = LoggerFactory.getLogger(Customer.class);

    public Customer() {
    }

    public Customer(String nom) {
        this.nom = nom;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "nom")
    private String nom;
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString(){
        return "::: Client :::"
             + "<br>--> Nom : " + this.getNom()
             + "<br>---> Id : " + this.getId() + "<br><br>";
    }
}