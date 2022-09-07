package com.example.old;

import com.example.beans.Book;

//import org.flywaydb.core.internal.jdbc.JdbcTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.util.List;

public class BaseBeanController<T extends IBaseBean> {

    private static final Logger log = LoggerFactory.getLogger(BaseBeanController.class);

    private T bean;

    /*@Autowired
    private JdbcTemplate jdbcTemplate;*/

    @PersistenceContext
    private EntityManager em;

    /*public BaseBeanController(String tableName){
        this.bean.setTableName(tableName);
    }*/

    public BaseBeanController(T bean){
        this.bean = bean;
    }

    public void insertuwu(String... args) throws Exception {
        String sql = "INSERT INTO " + bean.getTableName() + " (ID, ITEMID) VALUES (?; ?; ?)";

        /*jdbcTemplate.update(sql, 10, "un.email@gmail.com");
        int rows = jdbcTemplate.queryForInt(sql);
        if (rows > 0) {
            System.out.println("A new row has been inserted.");
        }*/
    }

    public List<Book> findAll(){
        String query = "SELECT * FROM " + bean.getTableName();
        TypedQuery<Book> resultList = em.createQuery(query, Book.class);

        return resultList.getResultList();
    }

    @Transactional
    public T save(T mb){
        em.persist(mb);
        return mb;
    }

    /*@Transactional
    public T findById(Long id){
        T mb = (T)em.find(T.class, id);
        return mb;
    }*/

    @Transactional
    public T findById(Long id, Class c){
        T mb = (T)em.find(c, id);
        return mb;
    }

    @Transactional
    public T update(T mb) {
        em.merge(mb);
        return mb;
    }

    @Transactional
    public T deleteById(long id, Class c) {
        T mb = (T) findById(id, c);
        if (mb != null) {
            em.remove(mb);
        }
        return mb;
    }

    @Transactional
    public int deleteAll() {
        Query query = em.createQuery("DELETE FROM " + bean.getTableName());
        return query.executeUpdate();
    }
}