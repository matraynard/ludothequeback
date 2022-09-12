package com.example.repository;

import com.example.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPageJpaRepository extends JpaRepository<Page, Long> {

    @Query(value = "select p from Page p where p.book.id =:bookId order by p.number")
    public List<Page> findAllOfBookId(Long bookId);

    @Query(value = "select p from Page p where p.number =:number order by p.id")
    public List<Page> findByNumber(int number);

    @Query(value = "select p from Page p where p.firstword like '%:firstWord%' order by p.firstword")
    public List<Page> findByFirstWord(String firstWord);

    @Query(value = "select p from Page p where p.number =:number and p.book.id =:bookId order by p.number")
    public Page findByNumberAndBookId(int number, Long bookId);
}