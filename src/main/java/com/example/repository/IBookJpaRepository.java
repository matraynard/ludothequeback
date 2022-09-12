package com.example.repository;

import com.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookJpaRepository extends JpaRepository<Book, Long> {

    @Query(value = "select b from Book b where b.title like '%:title%' order by b.title")
    public List<Book> findByTitle(String title);

    /*@Query(value = "select b from Book b order by b.title")
    public List<Book> findAll();*/
}
