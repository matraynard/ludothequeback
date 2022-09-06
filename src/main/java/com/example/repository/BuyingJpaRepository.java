package com.example.repository;

import com.example.beans.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyingJpaRepository extends JpaRepository<Buy, Long> {

    //@Query(value = "select b from Buy b where b.customer.id = :customerId and b.book.id = :bookId")
    @Query(value = "select b from Buy b")
    List<Buy> findByCustomerAndBook(Long customerId, Long bookId);
}
