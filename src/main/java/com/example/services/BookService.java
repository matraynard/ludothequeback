package com.example.services;

import com.example.beans.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Service
public class BookService {

    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    @PersistenceContext
    EntityManager entityManager;

    public List<Book> list() {
        return  entityManager.createNamedQuery("book.list", Book.class).getResultList();
    }

    public Book findById(Long id) {
        /*Query q = entityManager.createQuery("FROM customer c WHERE c.id = :id");
        q.setParameter("id", id);
        return (customer)q.getSingleResult();*/
        return (Book) entityManager.find(Book.class, id);
    }

    public List<Book> findByTitle(String name) {
        return entityManager.createQuery("SELECT b FROM book b WHERE b.title LIKE '%" + name + "%'", Book.class).getResultList();
    }

    @Transactional
    public void add(String titre) {
        entityManager.persist(new Book(titre));
    }
}