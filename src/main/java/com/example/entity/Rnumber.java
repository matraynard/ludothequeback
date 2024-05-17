package com.example.entity;

import javax.persistence.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
//@Table(name = "rnumber") //décommenter s'il n'y a pas de controller / service / repo
public class Rnumber {

    private static final Logger log = LoggerFactory.getLogger(Rnumber.class);

    // ------------- fields -------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isused", nullable = false)
    private boolean isUsed;

    // ------------- relationships -------------

    @OneToOne(optional=false, mappedBy="rnumber")
    public Book book;

    @OneToOne(optional=false, mappedBy="rnumber")
    public Game game;

    @OneToMany(mappedBy = "rnumber"/*, fetch = FetchType.EAGER*/)
    public List<Article> articles;

    // ------------- constructors -------------



    // ------------- getters -------------

    public Long getId() {
        return id;
    }

    public boolean isUsed() {
        return isUsed;
    }

    // ------------- setters -------------

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    // ------------- others -------------
}