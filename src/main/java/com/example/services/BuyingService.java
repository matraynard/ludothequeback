package com.example.services;

import com.example.beans.Buying;
import com.example.beans.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class BuyingService {

    private static final Logger log = LoggerFactory.getLogger(BuyingService.class);

    @PersistenceContext
    EntityManager entityManager;

    public List<Buying> list(){
        TypedQuery<Buying> query = entityManager.createNamedQuery("buying.list", Buying.class);
        return query.getResultList();
    }

    //@Transactional
    public Buying findById(Long id){
        return (Buying) entityManager.find(Buying.class, id);
    }

    public List<Buying> findByCustomer(Customer customer){
        return entityManager.createQuery("SELECT b FROM buy WHERE customer_id = " + customer.getId(), Buying.class).getResultList();
    }

    public Buying add(Buying buying){
        entityManager.persist(buying);
        return buying; //TODO vérifier si l'id est renseigné automatiquement
    }
}