package com.example.entity;

import javax.persistence.*;
import java.time.Instant;

//TODO this class should be removed, not used
@Entity
@Table(name = "offer_article")
public class OfferArticle {

    @Id
    private Long id;
    // ------------- fields -------------
    //x

    // ------------- relationships -------------
    @ManyToOne
    private Offer offer;

    @ManyToOne
    private Article article;

    // ------------- constructors -------------
    //x

    // ------------- getters -------------
    //x

    // ------------- setters -------------
    //x

    // ------------- others -------------
    //x
}