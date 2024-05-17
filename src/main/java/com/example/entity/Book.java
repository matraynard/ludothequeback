package com.example.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;

@Entity
//@NamedQuery(name = "book.list", query = "SELECT b FROM Book b ORDER BY b.id") //redondant avec findAll du BookJpaRepo, soit je commente ici, soit je commente findAll dans le repo
public class Book {

    private static final Logger log = LoggerFactory.getLogger(Book.class);

    // ------------- fields -------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "price", nullable = false)
    private int price;

    //@Column(name = "title", nullable = false)
    private String title;

    // ------------- relationships -------------
    /*@Column(name = "reference_id", nullable = false)
    private Long referenceId;*/
    /*@OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "rnumber",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "reference_id"))
    private ReferenceNumber referenceNumber;*/

    @OneToOne(optional=false/*, fetch = FetchType.LAZY*/)
    @JoinColumn(name="idrnumber", unique=true, nullable=false, updatable=false)
    public Rnumber rnumber;

    /*@Transient //permet d'indiquer que ce champ de l'entity n'est pas mappé à une colonne de la table en db; cette méthode est moins propre que d'avoir un bean dédié
    private Long pagecount;*/

    @OneToMany(/*cascade=ALL, */mappedBy="book", fetch = FetchType.EAGER/**/)
    public List<Page> pages;

    // ------------- constructors -------------
    public Book() {
    }
    public Book(String title, int price) {
        this.title = title;
        this.price = price;
    }

    // ------------- getters -------------
    public Long getId() {
        return id;
    }

    /*public Long getPagecount() {
        return pagecount;
    }*/

    public int getPrice(){
        return  this.price;
    }

    public Rnumber getRnumber() {
        return rnumber;
    }

    public String getTitle(){
        return  this.title;
    }

    // ------------- setters -------------
    public void setId(Long id) {
        this.id = id;
    }

    /*public void setPagecount(Long pagecount) {
        this.pagecount = pagecount;
    }*/

    public void setPrice(int price){
        this.price = price;
    }

    public void setRnumber(Rnumber rnumber) {
        this.rnumber = rnumber;
    }

    public void setTitle(String title){
        this.title = title;
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