package com.example.controller;

import com.example.bean.BookComplete;
import com.example.entity.Book;
import com.example.entity.Page;
import com.example.services.BookService;
import com.example.services.PageService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5173/")
@RequestMapping("/book/")
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;
    @Autowired
    private PageService pageService;

    /* -- DeleteMapping -- */

    @DeleteMapping(path = "{id}")
    public String delete(@PathVariable Long id){
        try{
            List<Page> pages = pageService.findAllOfBookId(id);
            pages.stream().forEach(p -> pageService.delete(p.getId()));
            Book book = bookService.delete(id);
            return "Le livre " + book.getTitle() + " a été supprimé. Ses pages aussi :'(";
        }
        catch(Error e){

        }
        return "Une erreur s'est produite, le livre n'a pas pu être supprimé.";
    }

    /* -- GetMapping -- */

    @GetMapping(path = "list")
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @GetMapping(path = "list/complete")
    public List<BookComplete> findAllComplete(){
        return bookService.findAllComplete();
    }

    @GetMapping(path = "{id}")
    public Book findById(@PathVariable Long id) throws IOException {
        Book book = bookService.findById(id);
        try{
            ObjectMapper om = new ObjectMapper();
            om.writeValue(new File("target/book.json"), book); //also works with .txt

            String filename = ZonedDateTime.now(ZoneId.of("Europe/Paris"))
                              .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                              + " BookController findById" + ".json";
            om.writeValue(new File("target/" + filename.trim()), book); //this one never work, idk why
        }
        catch(IOException ie){}

        return bookService.findById(id);
    }

    @GetMapping(path = "{id}/complete")
    public BookComplete findByIdCompleteBook(@PathVariable Long id){
        return bookService.findByIdCompleteBook(id);
    }

    @GetMapping(path = "t/{title}")
    public List<Book> findByTitle(@PathVariable String title){
        return bookService.findByTitle(title);
    }

    @GetMapping(path = "{id}/lastpage")
    public Page findLastPageById(@PathVariable Long id){
        return pageService.findLastOfBookId(id);
    }

    @GetMapping(path = "{id}/pagesnumber")
    public int findNumberOfPagesById(@PathVariable Long id){
        return bookService.findNumberOfPagesByBookId(id);
    }

    @GetMapping(path = "{id}/pages")
    public List<Page> findPagesById(@PathVariable Long id){
        return pageService.findAllOfBookId(id);
    }

    @GetMapping(path = "hello")
    public String hello(){
        return "C'est la page de hello world. Retourne travailler maintenant.";
    }

    @GetMapping(path = "read/{id}")
    public String readById(@PathVariable Long id){
        StringBuilder sb = new StringBuilder();
        List<Page> pages = pageService.findAllOfBookId(id);

        pages.stream().forEach(p -> sb.append("Page " + p.getNumber() + " : " + p.getFirstword() + "<br><br>"));
        return sb.toString();
    }

    @GetMapping(path = "readWithTitle/{id}")
    public String readWithTitleById(@PathVariable Long id){
        StringBuilder sb = new StringBuilder();
        sb.append("<b><u>" + bookService.findById(id).getTitle() + "</u></b><br><br><br>");
        sb.append(readById(id));
        return sb.toString();
    }

    /* -- PostMapping -- */

    @PostMapping(path = "{title}/{price}")
    public Book save(@PathVariable String title, @PathVariable int price){
        Book book = null;
        if (title != null && !title.isEmpty() && price > 0){
            book = new Book(title, price);
            bookService.add(book);
        }
        return book;
    }

    @PostMapping(path = "{title}/{price}/complete")
    public BookComplete saveComplete(@PathVariable String title, @PathVariable int price){
        BookComplete bookComplete = null;
        if (title != null && !title.isEmpty() && price > 0){
            Book book = new Book(title, price);
            bookService.add(book);
            bookComplete = new BookComplete(book, 0L);
        }
        return bookComplete;
    }

    /* -- PutMapping -- */

    @PutMapping(path = "{id}/{newTitle}")
    public Book update(@PathVariable Long id, @PathVariable String newTitle){
        return bookService.update(id, newTitle);
    }

    @PutMapping(path = "{id}/{newTitle}/complete")
    public BookComplete updateComplete(@PathVariable Long id, @PathVariable String newTitle){
        return bookService.updateComplete(id, newTitle);
    }
}