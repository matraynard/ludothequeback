package com.example.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
//@NamedQuery(name = "game.list", query = "SELECT g FROM Game g ORDER BY g.id") //redondant avec findAll du GameJpaRepo, soit je commente ici, soit je commente findAll dans le repo
public class Game {

    private static final Logger log = LoggerFactory.getLogger(Game.class);

    // ------------- fields -------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "price", nullable = false)
    private int price;

    //@Column(name = "title", nullable = false)
    private String title;

    // ------------- relationships -------------
    @OneToOne(optional=false/*, fetch = FetchType.LAZY*/)
    @JoinColumn(name="idrnumber", unique=true, nullable=false, updatable=false)
    public Rnumber rnumber;

    // ------------- constructors -------------
    public Game() {
    }
    public Game(String title, int price) {
        this.title = title;
        this.price = price;
    }

    // ------------- getters -------------
    public Long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public Rnumber getReferenceNumber() {
        return this.rnumber;
    }

    // ------------- setters -------------
    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReferenceNumber(Rnumber referenceNumber) {
        this.rnumber = referenceNumber;
    }

    // ------------- others -------------
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