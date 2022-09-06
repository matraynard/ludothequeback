package com.example.beans;

import javax.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@NamedQuery(name = "buy.list", query = "SELECT b FROM Buy b ORDER BY b.id")
public class Buy {

    private static final Logger log = LoggerFactory.getLogger(Buy.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Book> books;
    @ManyToOne
    private Customer customer;
    private ZonedDateTime buyingdate;


    public Buy() {
    }

    public Buy(Customer customer, Set<Book> books) {
        this.books = books;
        this.customer = customer;
    }


    public Set<Book> getBooks(){
        return this.books;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Long getId() {
        return id;
    }


    public void setBooks(Set<Book> books) {
        this.books = books;
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