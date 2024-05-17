package com.example.repository;

import com.example.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGameJpaRepository extends JpaRepository<Game, Long> {

    @Query(value = "select g from Game g where g.title like '%:title%' order by g.title")
    public List<Game> findByTitle(String title);

    @Query(value = "select g from Game g order by g.title")
    public List<Game> findAll();/**/ //redondant avec @NamedQuery(name = "game.list" de l'entity Game, soit je commente ici, soit je commente la namedQuery de l'entity
}