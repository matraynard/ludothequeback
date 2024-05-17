package com.example.repository;

import com.example.entity.Book;
import com.example.entity.Rnumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRnumberJpaRepository extends JpaRepository<Rnumber, Long> {

    @Query(value = "select r from Rnumber r order by r.id")
    public List<Rnumber> findAll();/**/ //redondant avec @NamedQuery(name = "book.list" de l'entity Book, soit je commente ici, soit je commente la namedQuery de l'entity
}