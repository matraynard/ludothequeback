package com.example.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "article")
public class Article {
    // ------------- fields -------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    // ------------- relationships -------------
    /*@ManyToMany(mappedBy = "articlesConcernesParOffre")*/
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "offer_article",
            joinColumns = { @JoinColumn(name = "idOffer") },
            inverseJoinColumns = { @JoinColumn(name = "idArticle") })
    List<Offer> offers;

    @ManyToOne
    @JoinColumn(name="idPurchase", nullable = false)
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name="idRnumber", nullable = false)
    public Rnumber rnumber;

    // ------------- constructors -------------
    //x

    // ------------- getters -------------
    //x

    // ------------- setters -------------
    //x

    // ------------- others -------------
    //x

    public Article(){}
}