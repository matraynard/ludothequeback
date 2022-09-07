package com.example.repository;

import com.example.beans.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPurchaseJpaRepository extends JpaRepository<Purchase, Long> {

    //@Query(value = "select p from Purchase p where p.customer.id =:customerId")
    @Query(value = "select p from Purchase p join p.books bo where p.customer.id =:customerId and bo.id =:bookId")
    List<Purchase> findByCustomerAndBook(Long customerId, Long bookId);

    @Query(value = "select p from Purchase p join p.books bo where p.customer.id =:customerId and bo.id =:bookId")
    List<Purchase> findByCustomer(Long customerId);


}