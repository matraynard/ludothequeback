package com.example.beans;

import javax.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;

@Entity
@Table(name = "buying", schema = "schema1")
@NamedQuery(name = "buying.list", query = "SELECT b FROM Buying b ORDER BY b.id")
public class Buying {

    private static final Logger log = LoggerFactory.getLogger(Buying.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Customer customer;

    private ZonedDateTime buyingdate;


    public Buying() {
    }

    public Buying(Customer customer, Book book) {
        this.book = book;
        this.customer = customer;
    }


    public Book getBook(){
        return this.book;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Long getId() {
        return id;
    }


    public void setBook(Book book) {
        this.book = book;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @Override
    public String toString(){
        return "::: Client :::"
                + "<br>--> Nom : " + this.getCustomer()
                + "<br>---> Id : " + this.getId() + "<br><br>";
    }
}