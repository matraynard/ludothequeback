package com.example.entity;

import javax.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Entity
@NamedQuery(name = "purchase.list", query = "SELECT p FROM Purchase p ORDER BY p.id")
public class Purchase {

    private static final Logger log = LoggerFactory.getLogger(Purchase.class);

    // ------------- fields -------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant creationDate;
    private Instant validationDate;

    // ------------- relationships -------------
    @ManyToOne//(mappedBy = "customer")
    @JoinColumn(name="idCustomer", nullable = false) //is this necessary ?
    private Customer customer;

    @OneToMany/*(fetch = FetchType.EAGER)
    @JoinTable(name = "article",
               joinColumns = @JoinColumn(name = "id"),
               inverseJoinColumns = @JoinColumn(name = "purchase_id"))*/
    private List<Article> articles;
    //private List<Book> books = new ArrayList<>(); //cette forme est-elle aussi valide ?


    // ------------- constructors -------------
    public Purchase() {
    }

    public Purchase(Customer customer, List<Article> articles, Instant creationDate) {
        this.articles = articles;
        this.customer = customer;
        this.creationDate = creationDate;
    }

    public Purchase(Customer customer, Instant creationDate) {
        this.customer = customer;
        this.creationDate = creationDate;
    }

    // ------------- getters -------------
    public List<Article> getArticles(){
        return this.articles;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Long getId() {
        return this.id;
    }

    public Instant getPurchaseCreationDate() {
        return this.creationDate;
    }

    public Instant getPurchaseValidationDate() {
        return this.validationDate;
    }

    // ------------- setters -------------
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPurchaseCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public void setPurchaseValidationDate(Instant validationDate) {
        this.validationDate = validationDate;
    }

    // ------------- others -------------
    @Override
    public String toString(){
        return "::: Client :::"
                + "<br>--> Nom : " + this.getCustomer()
                + "<br>---> Id : " + this.getId() + "<br><br>";
    }
}