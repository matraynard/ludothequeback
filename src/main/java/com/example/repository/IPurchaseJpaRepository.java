package com.example.repository;

import com.example.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface IPurchaseJpaRepository extends JpaRepository<Purchase, Long> {

    //@Query(value = "select p from Purchase p where p.customer.id =:customerId")
    @Query(value = "select p from Purchase p join p.articles ar where p.customer.id =:customerId and ar.id =:articleId")
    List<Purchase> findByCustomerAndArticle(Long customerId, Long articleId);

    /*@Query(value = "select p from Purchase p where :articleId IN p.articles.id")
    List<Purchase> findByArticle(Long articleId);*/

    @Query(value = "select p from Purchase p where p.customer.id =:customerId")
    List<Purchase> findByCustomer(Long customerId);

    @Query(value = "select p from Purchase p where p.creationDate =:date")
    List<Purchase> findByCreationDate(Instant date);

    @Query(value = "select p from Purchase p where p.validationDate =:date")
    List<Purchase> findByValidationDate(Instant date);

}