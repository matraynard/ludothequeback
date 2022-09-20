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
@CrossOrigin(origins = "http://127.0.0.1:5173/")
@RequestMapping("/page/")
public class PageController {

    private static final Logger log = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private PageService pageService;
    @Autowired
    private BookService bookService;

    /* -- DeleteMapping -- */

    @DeleteMapping(path = "{id}")
    public String delete(@PathVariable Long id){
        Page page = pageService.delete(id);
        List<Page> pagesAfter = pageService.findAfterNumber(page.getBook().getId(), page.getNumber());
        if (pagesAfter != null){
            pagesAfter.stream().forEach(p -> p.setNumber(p.getNumber() - 1));
            pagesAfter.stream().forEach(p -> pageService.update(p));
        }
        return "La page " + page.getNumber() + " du livre " + page.getBook().getTitle() + "a été supprimée.";
    }

    @DeleteMapping(path = "book/{id}/number/{number}") //TODO à supprimer ?
    public String delete(@PathVariable Long bookId, @PathVariable int number){
        Page page = pageService.delete(bookId, number);
        List<Page> pagesAfter = pageService.findAfterNumber(bookId, number);
        if (pagesAfter != null){
            pagesAfter.stream().forEach(p -> p.setNumber(p.getNumber() - 1));
            pagesAfter.stream().forEach(p -> pageService.update(p));
        }
        return "La page " + page.getNumber() + " du livre " + page.getBook().getTitle() + "a été supprimée.";
    }

    /* -- GetMapping -- */

    @GetMapping(path = "list")
    public List<Page> findAll(){
        return pageService.findAll();
    }


    @GetMapping(path = "list/{bookId}")
    private List<Page> findAllOfBookId(@PathVariable Long bookId){
        return pageService.findAllOfBookId(bookId);
    }

    @GetMapping(path = "fw/{firstWord}")
    private List<Page> findByFirstWord(@PathVariable String firstWord){
        return pageService.findByFirstWord(firstWord);
    }

    @GetMapping(path = "{id}")
    private Page findById(@PathVariable Long id){
        return pageService.findById(id);
    }

    @GetMapping(path = "n/{number}")
    private List<Page> findByNumber(@PathVariable int number){
        return pageService.findByNumber(number);
    }

    @GetMapping(path = "n/{number}/b/{bookId}")
    private Page findByNumberAndBookId(@PathVariable int number, @PathVariable Long bookId){
        return pageService.findByNumberAndBookId(number, bookId);
    }

    @GetMapping(path = "hello")
    public String hello(){
        return "C'est la page de hello world. Retourne travailler maintenant.";
    }

    /* -- PostMapping -- */

    @PostMapping(path = "{bookId}/{number}/{pageFirstWord}")
    public Page save(@PathVariable Long bookId, @PathVariable int number, @PathVariable String pageFirstWord){
        Page page = new Page(bookService.findById(bookId), number);
        page.setFirstword(pageFirstWord);
        pageService.add(page);
        return page;
    }

    @PostMapping(path = "{bookId}/{pageFirstWord}")
    public Page save(@PathVariable Long bookId, @PathVariable String pageFirstWord){
        Page lastPage = pageService.findLastOfBookId(bookId);
        Page newPage = lastPage != null
                ? new Page(bookService.findById(bookId), pageService.findLastOfBookId(bookId).getNumber() + 1)
                : new Page(bookService.findById(bookId), 1);
        newPage.setFirstword(pageFirstWord);
        pageService.add(newPage);
        return newPage;
    }

    /* -- PutMapping -- */

    @PutMapping(path = "{pageId}/{newFirstWord}")
    public Page update(@PathVariable Long pageId, @PathVariable String newFirstWord){
        return pageService.update(pageId, newFirstWord);
    }
}