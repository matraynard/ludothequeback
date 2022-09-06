package com.example.beans;

import com.example.interfaces.IBaseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;

@Entity
@Table(name="livre", schema = "schema1")
@NamedQuery(name = "livre.list", query = "SELECT l FROM Livre l ORDER BY l.id")
public class Livre implements IBaseBean {

    private static final Logger log = LoggerFactory.getLogger(Livre.class);

    public Livre() {
    }

    public Livre(int prix) {
        this.prix = prix;
    }

    public Livre(String title) {
        this.prix = prix;
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

    @Column(name = "prix", nullable = false)
    private int prix;
    public int getPrix(){
        return  this.prix;
    }
    public void setPrix(int prix){
        this.prix = prix;
    }

    @Column(name = "titre", nullable = false)
    private String title;
    public String getTitle(){
        return  this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    @Override
    public String getTableName() {
        return "livre";
    }

    //public void setTableName(String tableName){this.table = tableName;}

    @Override
    public String toString(){
        return "Elément de ABSTRACTITEM_A : id = " + id + ", itemId = " + prix;
    }
}