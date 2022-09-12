package com.example.controller;

import com.example.entity.Page;
import com.example.services.BookService;
import com.example.services.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/page")
public class PageController {

    private static final Logger log = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private PageService pageService;

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable Long id){
        Page page = pageService.delete(id);
        return "La page " + page.getNumber() + " du livre " + page.getBook().getTitle() + "a été supprimée.";
    }

    @GetMapping(path = "/list")
    public List<Page> findAll(){
        return pageService.findAll();
    }


    @GetMapping(path = "/list/{bookId}")
    private List<Page> findAllOfBookId(@PathVariable Long bookId){
        return pageService.findAllOfBookId(bookId);
    }

    @GetMapping(path = "/{id}")
    private Page findById(@PathVariable Long id){
        return pageService.findById(id);
    }

    @GetMapping(path = "/fw/{firstWord}")
    private List<Page> findByFirstWord(@PathVariable String firstWord){
        return pageService.findByFirstWord(firstWord);
    }

    @GetMapping(path = "/n/{number}")
    private List<Page> findByNumber(@PathVariable int number){
        return pageService.findByNumber(number);
    }

    @GetMapping(path = "/n/{number}/b/{bookId}")
    private Page findByNumberAndBookId(@PathVariable int number, Long bookId){
        return pageService.findByNumberAndBookId(number, bookId);
    }

    @GetMapping(path = "/hello")
    public String hello(){
        return "C'est la page de hello world. Retourne travailler maintenant.";
    }

    @PostMapping(path = "/{bookId}/{number}")
    public Page save(@PathVariable Long bookId, @PathVariable int number){
        Page customer = new Page(new BookService().findById(bookId), number);
        pageService.add(customer);
        return customer;
    }

    @PutMapping(path = "/{pageId}/{newFirstWord}")
    public Page update(@PathVariable Long pageId, @PathVariable String newFirstWord){
        return pageService.update(pageId, newFirstWord);
    }
}