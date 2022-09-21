package com.example.repository;

import com.example.bean.BookComplete;
import com.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookJpaRepository extends JpaRepository<Book, Long> {

    @Query(value = "select b from Book b where b.title like '%:title%' order by b.title")
    public List<Book> findByTitle(String title);

    @Query(value = "select count (*) from Page p where p.book.id =:bookId")
    public Long findNumberOfPagesByBookId(Long bookId);

    @Query(value = "select new com.example.bean.BookComplete(b, count(b)) from Book b where b.id =:bookId group by b.id")
    public BookComplete findBookCompleteById(Long bookId);

    /*@Query(value = "select b from Book b order by b.title")
    public List<Book> findAll();*/
}
