package com.example.beans;

import com.example.interfaces.IBaseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;

@Entity
@Table(name="page", schema = "schema1")
@NamedQuery(name = "page.list", query = "SELECT p FROM Page p ORDER BY p.id")
public class Page implements IBaseBean {

    private static final Logger log = LoggerFactory.getLogger(Page.class);

    public Page() {
    }

    public Page(Livre livre, int numero) {
        this.livre = livre;
        this.number = numero;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    private Livre livre;
    public Livre getLivre(){
        return  this.livre;
    }
    public void setLivre(Livre livre){
        this.livre = livre;
    }

    @Column(name = "numero", nullable = false)
    private int number;
    public int getNumber(){
        return  this.number;
    }
    public void setNumber(int number){
        this.number = number;
    }

    @Override
    public String getTableName() {
        return "page";
    }

    @Override
    public String toString(){
        return "Elément de ABSTRACTITEM_B : id = " + id + ", itemId = " + livre;
    }
}