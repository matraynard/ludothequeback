package com.example.controllers.mapping;

import com.example.beans.Book;
import com.example.beans.Page;
import com.example.services.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/page")
public class PageMappingController {

    private static final Logger log = LoggerFactory.getLogger(PageMappingController.class);

    @Autowired
    private PageService pageService;

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public List<Page> list(){
        return pageService.list();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getOne/{id}")
    public String findStringValueById(@PathVariable Long id){
        return findPageById(id).toString();
    }

    private Page findPageById(@PathVariable Long id){
        return pageService.findById(id);
    }

    /*@RequestMapping(method = RequestMethod.GET, path = "/getAll")
    public String findAllPagesStringValueByName(){

    }*/

    //pages, méthodes à implémenter :
    //- toutes les pages overall
    //- toutes les pages d'un livre
    //- toutes les pages du même number
    //- une page du livre et du number

    @RequestMapping(method = RequestMethod.PUT, path = "/livre/{id}/page/{number}")
    public Page add(@PathVariable Long id, @PathVariable int number){
        return pageService.add(new Book(id), number);
    }
}
