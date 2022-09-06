package com.example.services;

import com.example.beans.Livre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

@Service
public class LivreService {

    private static final Logger log = LoggerFactory.getLogger(LivreService.class);

    @PersistenceContext
    EntityManager entityManager;

    public List<Livre> list() {
        TypedQuery<Livre> query = entityManager.createNamedQuery("livre.list", Livre.class);
        return query.getResultList();
    }

    @Transactional
    public Livre findById(Long id) {
        /*Query q = entityManager.createQuery("FROM customer c WHERE c.id = :id");
        q.setParameter("id", id);
        return (customer)q.getSingleResult();*/
        Livre response = (Livre) entityManager.find(Livre.class, id);
        return response;
    }

    public List<Livre> findByTitle(String name) {
        TypedQuery<Livre> response = entityManager.createQuery("SELECT l FROM Livre l WHERE l.titre LIKE '%" + name + "%'", Livre.class);
        return response.getResultList();
    }

    public void add(String titre) {
        Livre l = new Livre(titre);
        entityManager.persist(l);
    }
}