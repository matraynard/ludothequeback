package com.example.controller;

import com.example.bean.BookBean;
import com.example.entity.Book;
import com.example.entity.Page;
import com.example.services.BookService;
import com.example.services.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<BookBean> findAllComplete(){
        return bookService.findAllComplete();
    }

    @GetMapping(path = "{id}")
    public Book findById(@PathVariable Long id){
        return bookService.findById(id);
    }

    @GetMapping(path = "{id}/complete")
    public BookBean findByIdCompleteBook(@PathVariable Long id){
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

    /* -- PutMapping -- */

    @PutMapping(path = "{id}/{newTitle}")
    public Book update(@PathVariable Long id, @PathVariable String newTitle){
        return bookService.update(id, newTitle);
    }
}