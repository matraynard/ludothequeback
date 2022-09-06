package com.example.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Entity
public abstract class BaseBean {

    private static final Logger log = LoggerFactory.getLogger(BaseBean.class);

    /*@Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ITEMID", nullable = false)
    private int itemid;*/

    private String table;

    BaseBean(){};

    BaseBean(String tableName, int itemId) {
        this.table = tableName;
        //this.itemid = itemId;
    }

    /*public Long getId() {
        return id;
    }

    public int getItemid(){
        return  this.itemid;
    }

    public String getTableName(){ return this.table;}

    public void setId(Long id) {
        this.id = id;
    }

    public void setItemId(int itemId){
        this.itemid = itemId;
    }

    public void setTableName(String tableName){this.table = tableName;}*/

    /*@Override
    public String toString(){
        return "Elément de " + table + " : id = " + this.getId() + ", itemId = " + this.getItemid();
    }*/
}