package com.example.repository;

import com.example.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerJpaRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select c from Customer c where c.name like '%:name%' order by c.name")
    public List<Customer> findByName(String name);
}