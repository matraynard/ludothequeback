package com.example.services;

import com.example.entity.Article;
import com.example.entity.Book;
import com.example.entity.Purchase;
import com.example.entity.Customer;
import com.example.repository.IPurchaseJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.util.*;

@Service
public class PurchaseService {

    private static final Logger log = LoggerFactory.getLogger(PurchaseService.class);

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    IPurchaseJpaRepository repository;

    @Transactional
    public Purchase ceateNewbasket(Instant date, Long customerId) {
        if (date != null){
            Customer customer = new CustomerService().findById(customerId);
            List<Article> articles = new ArrayList<Article>();
            return ceateNewbasket(new Purchase(customer, articles, date));
        }
        return null;
    }

    @Transactional
    public Purchase ceateNewbasket(Purchase purchase) {
        if (purchase != null){
            repository.save(purchase);
            return purchase;
        }
        return null;
    }

    @Transactional
    public Purchase delete(Long id) {
        return delete(findById(id));
    }

    @Transactional
    public Purchase delete(Purchase purchase) {
        if (purchase != null){
            repository.delete(purchase);
            return purchase;
        }
        return null;
    }

    public List<Purchase> findAll() {
        return  repository.findAll(Sort.by(Sort.Direction.ASC, "purchaseCreationDate"));
    }

    /**/public List<Purchase> findByCustomerAndBook(Long idCustomer, Long idLivre) {
        List<Purchase> foundPurchases = repository.findByCustomerAndArticle(idLivre, idCustomer);
        foundPurchases.sort(Comparator.comparing(Purchase::getPurchaseCreationDate));
        return foundPurchases;
    }

    public List<Purchase> findByCustomer(Customer customer) {
        return customer != null
                ? findByCustomer(customer.getId())
                : null;
    }

    public List<Purchase> findByCustomer(Long customerId) {
        List<Purchase> foundPurchases = repository.findByCustomer(customerId);
        foundPurchases.sort(Comparator.comparing(Purchase::getPurchaseCreationDate));
        return foundPurchases;
    }

    public List<Purchase> findByCreationDate(Instant date) {
        if (date != null){
            List<Purchase> foundPurchases = repository.findByCreationDate(date);
            foundPurchases.sort(Comparator.comparing(Purchase::getPurchaseCreationDate));
            return foundPurchases;
        }
        return null;
    }

    public Purchase findById(Long id) {
        return repository.findById(id).get();
    }

    @Transactional
    public Purchase setCustomer(Long purchaseId, Long customerId) {
        Purchase purchase = findById(purchaseId);
        Customer customer = new CustomerService().findById(customerId);
        if (purchase != null && customer != null){
            purchase.setCustomer(customer);
            return saveCustomer(purchase);
        }
        return null;
    }

    @Transactional
    public Purchase saveCustomer(Purchase purchase) {
        repository.save(purchase);
        return purchase;
    }
}