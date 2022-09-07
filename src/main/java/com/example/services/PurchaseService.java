package com.example.services;

import com.example.beans.Book;
import com.example.beans.Purchase;
import com.example.beans.Customer;
import com.example.repository.IPurchaseJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class PurchaseService {

    private static final Logger log = LoggerFactory.getLogger(PurchaseService.class);

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    IPurchaseJpaRepository purchaseJpaRepository;

    public List<Purchase> list(){
        TypedQuery<Purchase> query = entityManager.createNamedQuery("purchase.list", Purchase.class);
        return query.getResultList();
    }

    //@Transactional
    public Purchase findById(Long id){
        return (Purchase) entityManager.find(Purchase.class, id);
    }

    public Purchase findByBookAndCustomer(Long idLivre, Long idCustomer) {
        Customer customer = new CustomerService().findById(idCustomer);
        Book book = new BookService().findById(idLivre);
        //Set<Book> books = new HashSet<Book>(){{add(new BookService().findById(idL));}};


        //return entityManager.createQuery("SELECT p FROM purchase WHERE book_id = " + book.getId() + " AND customer_id = " + customer.getId(), Purchase.class).getSingleResult();
        return purchaseJpaRepository.findByCustomerAndBook(idLivre, idCustomer).get(0);
        /*return entityManager
                .createQuery("select p from Purchase p join p.books bo where p.customer.id =:customerId and bo.id =:bookId", Purchase.class)
                .getSingleResult();*/

    }

    public List<Purchase> findByCustomer(Long idCustomer){
        //return entityManager.createQuery("SELECT p FROM purchase WHERE customer_id = " + customer.getId(), Purchase.class).getResultList();
        return purchaseJpaRepository.findByCustomer(idCustomer);
    }

    public Purchase add(Purchase purchase){
        entityManager.persist(purchase);
        return purchase; //TODO vérifier si l'id est renseigné automatiquement
    }
}