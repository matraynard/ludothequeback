package com.example.services;

import com.example.beans.Book;
import com.example.beans.Buy;
import com.example.beans.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class BuyingService {

    private static final Logger log = LoggerFactory.getLogger(BuyingService.class);

    @PersistenceContext
    EntityManager entityManager;

    public List<Buy> list(){
        TypedQuery<Buy> query = entityManager.createNamedQuery("buying.list", Buy.class);
        return query.getResultList();
    }

    //@Transactional
    public Buy findById(Long id){
        return (Buy) entityManager.find(Buy.class, id);
    }

    public Buy findByBookAndCustomer(Long idL, Long idC) {
        Customer customer = new CustomerService().findById(idC);
        Book book = new BookService().findById(idL);
        //Set<Book> books = new HashSet<Book>(){{add(new BookService().findById(idL));}};


        //return entityManager.createQuery("SELECT b FROM buy WHERE book_id = " + book.getId() + " AND customer_id = " + customer.getId(), Buying.class).getSingleResult();
        return entityManager.createQuery(
                "select b from Buy b where b.customer.id = :customerId and b.book.id = :bookId", Buy.class
                ).getSingleResult();
    }

    public List<Buy> findByCustomer(Customer customer){
        return entityManager.createQuery("SELECT b FROM buy WHERE customer_id = " + customer.getId(), Buy.class).getResultList();
    }

    public Buy add(Buy buying){
        entityManager.persist(buying);
        return buying; //TODO vérifier si l'id est renseigné automatiquement
    }
}