package com.example.services;

import com.example.beans.Book;
import com.example.beans.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

@Service
public class PageService {

    private static final Logger log = LoggerFactory.getLogger(PageService.class);

    @PersistenceContext
    EntityManager entityManager;

    public List<Page> list() {
        TypedQuery<Page> query = entityManager.createNamedQuery("page.list", Page.class);
        return query.getResultList();
    }

    @Transactional
    public Page findById(Long id) {
        /*Query q = entityManager.createQuery("FROM customer c WHERE c.id = :id");
        q.setParameter("id", id);
        return (customer)q.getSingleResult();*/
        Page response = (Page) entityManager.find(Page.class, id);
        return response;
    }

    public Page findByNumberAndBookId(int number, Book book) {
        Page response = (Page) entityManager.createQuery("SELECT p FROM Page p WHERE p.number =" + number + " AND p.book_id = " + book.getId(), Page.class);
        return response;
    }

    public Page add(Book book, int number) {
        Page p = new Page(book, number);
        entityManager.persist(p);
        return p;
    }
}