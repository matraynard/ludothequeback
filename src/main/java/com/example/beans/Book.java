package com.example.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;

@Entity
@Table(name="book", schema = "schema1")
@NamedQuery(name = "book.list", query = "SELECT b FROM Book b ORDER BY b.id")
public class Book {

    private static final Logger log = LoggerFactory.getLogger(Book.class);

    public Book() {
    }

    public Book(int price) {
        this.price = price;
    }

    public Book(String title) {
        this.title = title;
    }

    public Book(Long id){
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "price", nullable = false)
    private int price;
    public int getPrice(){
        return  this.price;
    }
    public void setPrice(int price){
        this.price = price;
    }

    @Column(name = "title", nullable = false)
    private String title;
    public String getTitle(){
        return  this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getTableName() {
        return "book";
    }

    //public void setTableName(String tableName){this.table = tableName;}

    @Override
    public String toString(){
        return "::: Livre :::"
                + "<br>--> Titre : " + this.getTitle()
                + "<br>---> Prix : " + this.getPrice() + " €<br><br>";
    }
}