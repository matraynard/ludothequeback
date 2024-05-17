package com.example.services;

import com.example.entity.Game;
import com.example.repository.IGameJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.List;

@Service
public class GameService {

    private static final Logger log = LoggerFactory.getLogger(GameService.class);

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    IGameJpaRepository repository;

    @Transactional
    public Game add(String title, int price) {
        return title != null
                ? add(new Game(title, price))
                : null;
    }

    @Transactional
    public Game add(Game game) {
        if (game != null){
            repository.save(game);
            return game;
        }
        return null;
    }

    @Transactional
    public Game delete(Long id) {
        return delete(findById(id));
    }

    @Transactional
    public Game delete(Game game) {
        if (game != null){
            repository.delete(game);
            return game;
        }
        return null;
    }

    //identique à public List<Game> list()
    public List<Game> findAll() {
        List<Game> games = repository.findAll(Sort.by(Sort.Direction.ASC, "title"));
        return games;
    }

    public Game findById(Long id) {
        return repository.findById(id).get();
    }

    public List<Game> findByTitle(String title) {
        if (title != null){
            List<Game> foundBooks = repository.findByTitle(title);
            foundBooks.sort(Comparator.comparing(Game::getTitle));
            return foundBooks;
        }
        return null;
    }

    @Transactional //TODO ne fonctionnait pas quand j'avais pas aussi cette annot' ici
    public Game update(Long id, String newTitle) {
        Game game = findById(id);
        if (newTitle != null && game != null){
            game.setTitle(newTitle);
            return update(game);
        }
        return game;
    }

    @Transactional
    public Game update(Game game) {
        repository.save(game);
        return game;
    }
}