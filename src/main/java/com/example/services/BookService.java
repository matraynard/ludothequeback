package com.example.services;

import com.example.bean.BookComplete;
import com.example.entity.Book;
import com.example.repository.IBookJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BookService {

    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    IBookJpaRepository repository;

    @Transactional
    public Book add(String title, int price) {
        return title != null
                ? add(new Book(title, price))
                : null;
    }

    @Transactional
    public Book add(Book book) {
        if (book != null){
            repository.save(book);
            return book;
        }
        return null;
    }

    @Transactional
    public Book delete(Long id) {
        return delete(findById(id));
    }

    @Transactional
    public Book delete(Book book) {
        if (book != null){
            repository.delete(book);
            return book;
        }
        return null;
    }

    //identique à public List<Book> list()
    public List<Book> findAll() {
        List<Book> books = repository.findAll(Sort.by(Sort.Direction.ASC, "title"));
        return books;
    }

    public List<BookComplete> findAllComplete() {
        List<Book> books = repository.findAll(Sort.by(Sort.Direction.ASC, "title"));
        List<BookComplete> bookCompletes = new ArrayList<>();
        books.stream().forEach(book -> bookCompletes.add(new BookComplete(book, (repository.findNumberOfPagesByBookId(book.getId())))));
        return bookCompletes;
    }

    public Book findById(Long id) {
        return repository.findById(id).get();
    }

    public BookComplete findByIdCompleteBook(Long id){
        return repository.findBookCompleteById(id);
    }

    public List<Book> findByTitle(String title) {
        if (title != null){
            List<Book> foundBooks = repository.findByTitle(title);
            foundBooks.sort(Comparator.comparing(Book::getTitle));
            return foundBooks;
        }
        return null;
    }

    public int findNumberOfPagesByBookId(Long id){
        return 0;
    }

    @Transactional //TODO ne fonctionnait pas quand j'avais pas aussi cette annot' ici
    public Book update(Long id, String newTitle) {
        Book book = findById(id);
        if (newTitle != null && book != null){
            book.setTitle(newTitle);
            return update(book);
        }
        return book;
    }

    @Transactional
    public Book update(Book book) {
        repository.save(book);
        return book;
        //return entityManager.merge(book);
        //entityManager.flush(); //peut servir si des fois la modif ne fonctionne pas
    }
}