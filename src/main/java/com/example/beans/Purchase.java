package com.example.beans;

import javax.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Set;

@Entity
@NamedQuery(name = "purchase.list", query = "SELECT p FROM Purchase p ORDER BY p.id")
public class Purchase {

    private static final Logger log = LoggerFactory.getLogger(Purchase.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "purchase_book",
               joinColumns = @JoinColumn(name = "book_id"),
               inverseJoinColumns = @JoinColumn(name = "purchase_id"))
    private Set<Book> books;
    //private List<Book> books = new ArrayList<>(); //cette forme est-elle aussi valide ?
    @ManyToOne
    private Customer customer;
    private Instant purchasedate;


    public Purchase() {
    }

    public Purchase(Customer customer, Set<Book> books) {
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