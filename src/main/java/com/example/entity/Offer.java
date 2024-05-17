package com.example.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "offer")
public class Offer {

    // ------------- fields -------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private int offerValue;

    private Instant startValidationDate;

    private Instant endValidationDate;

    // ------------- relationships -------------
    /*@OneToMany(cascade=ALL, mappedBy="offer")
    public List<OfferArticle> offerArticles;*/
    /*@ManyToMany
    @JoinTable(name = "offer_article",
               joinColumns = @JoinColumn(name = "idOffer"),
               inverseJoinColumns = @JoinColumn(name = "idArticle"))*/
    @ManyToMany(mappedBy = "offers")
    List<Article> articlesConcernesParOffre;

    /*@ManyToMany
@JoinTable(
  name = "course_like",
  joinColumns = @JoinColumn(name = "student_id"),
  inverseJoinColumns = @JoinColumn(name = "course_id"))
Set<Course> likedCourses;*/

    // ------------- constructors -------------
    //x

    // ------------- getters -------------
    //x

    // ------------- setters -------------
    //x

    // ------------- others -------------
    //x
}