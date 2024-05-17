package com.example.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;

@Entity
@NamedQuery(name = "page.list", query = "SELECT p FROM Page p ORDER BY p.id")
public class Page {

    private static final Logger log = LoggerFactory.getLogger(Page.class);

    // ------------- fields -------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    private String firstword;

    // ------------- relationships -------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idbook", nullable = false)
    private Book book;

    // ------------- constructors -------------
    public Page() {
    }
    public Page(Book book, int number) {
        this.book = book;
        this.number = number;
    }

    // ------------- getters -------------
    public Book getBook(){
        return  this.book;
    }

    public String getFirstword() {
        return firstword;
    }

    public Long getId() {
        return id;
    }

    public int getNumber(){
        return  this.number;
    }

    // ------------- setters -------------
    public void setBook(Book book){
        this.book = book;
    }

    public void setFirstword(String firstword) {
        this.firstword = firstword;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(int number){
        this.number = number;
    }

    // ------------- others -------------
    @Override
    public String toString(){
        //return "Elément de ABSTRACTITEM_B : id = " + id + ", itemId = " + book;
        return "::: Page :::"
                + "<br>--> Number : " + this.getNumber()
                + "<br>---> Livre : " + this.getBook().getTitle() + "<br><br>";
    }
}