package com.example.old;

import com.example.beans.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

//@Service
public class PageController {

    private static final Logger log = LoggerFactory.getLogger(PageController.class);

    private String tableName;

    /*@Autowired
    private JdbcTemplate jdbcTemplate;*/

    @PersistenceContext
    private EntityManager em;

    public PageController(String tableName){
        this.tableName = tableName;
    }

    /*public void insertuwu(String... args) throws Exception {
        String sql = "INSERT INTO " + this.tableName + " (ID, ITEMID) VALUES (?; ?; ?)";

        jdbcTemplate.update(sql, 10, "ravi.kumar@gmail.com");
        int rows = jdbcTemplate.queryForInt(sql);
        if (rows > 0) {
            System.out.println("A new row has been inserted.");
        }
    }*/

    @Transactional
    public List<Page> findAll(){
        //String query = "SELECT * FROM ABSTRACTITEM_B"/* + this.tableName*/;
        String query = "SELECT * FROM page"/* + this.tableName*/;
        TypedQuery<Page> resultList = this.em.createQuery(query, Page.class);

        return resultList.getResultList();
    }

    @Transactional
    public Page save(Page mb){
        em.persist(mb);
        return mb;
    }

    @Transactional
    public Page findById(Long id){
        Page mb = (Page)em.find(Page.class, id);
        return mb;
    }

    @Transactional
    public Page update(Page mb) {
        em.merge(mb);
        return mb;
    }

    @Transactional
    public Page deleteById(long id) {
        Page mb = findById(id);
        if (mb != null) {
            em.remove(mb);
        }
        return mb;
    }

    @Transactional
    public int deleteAll() {
        Query query = em.createQuery("DELETE FROM " + this.tableName);
        return query.executeUpdate();
    }
}