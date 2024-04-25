package com.example.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@NamedQuery(name = "book.list", query = "SELECT b FROM Book b ORDER BY b.id") //redondant avec findAll du BookJpaRepo
public class Book {

    private static final Logger log = LoggerFactory.getLogger(Book.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "title", nullable = false)
    private String title;

    /*@Transient //permet d'indiquer que ce champ de l'entity n'est pas mappé à une colonne de la table en db; cette méthode est moins propre que d'avoir un bean dédié
    private Long pagecount;*/


    public Book() {
    }
    public Book(String title, int price) {
        this.title = title;
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    /*public Long getPagecount() {
        return pagecount;
    }*/

    public int getPrice(){
        return  this.price;
    }

    public String getTitle(){
        return  this.title;
    }


    public void setId(Long id) {
        this.id = id;
    }

    /*public void setPagecount(Long pagecount) {
        this.pagecount = pagecount;
    }*/

    public void setPrice(int price){
        this.price = price;
    }

    public void setTitle(String title){
        this.title = title;
    }


    @Override
    public String toString(){
        return "::: Livre :::"
                + "<br>--> Titre : " + this.getTitle()
                + "<br>---> Prix : " + this.getPrice() + " €<br><br>";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}