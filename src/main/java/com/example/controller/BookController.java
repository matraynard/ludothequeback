package com.example.controller;

import com.example.entity.Book;
import com.example.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable Long id){
        Book book = bookService.delete(id);
        return "Le livre " + book.getTitle() + "a été supprimé.";
    }

    @GetMapping(path = "/list")
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Book findById(@PathVariable Long id){
        return bookService.findById(id);
    }

    @GetMapping(path = "/{title}")
    public List<Book> findByTitle(@PathVariable String title){
        return bookService.findByTitle(title);
    }

    @GetMapping(path = "/hello")
    public String hello(){
        return "C'est la page de hello world. Retourne travailler maintenant.";
    }

    @PostMapping(path = "/{title}/{price}")
    public Book save(@PathVariable String title, @PathVariable int price){
        Book book = new Book(title, price);
        bookService.add(book);
        return book;
    }

    @PutMapping(path = "/{id}/{newTitle}")
    public Book update(@PathVariable Long id, @PathVariable String newTitle){
        return bookService.update(id, newTitle);
    }
}