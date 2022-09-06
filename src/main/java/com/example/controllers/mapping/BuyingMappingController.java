package com.example.controllers.mapping;

import com.example.beans.Book;
import com.example.beans.Buy;
import com.example.beans.Customer;
import com.example.services.BookService;
import com.example.services.BuyingService;
import com.example.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/buy")
public class BuyingMappingController {

    private static final Logger log = LoggerFactory.getLogger(BuyingMappingController.class);

    @Autowired
    private BuyingService buyingService;

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public List<Buy> list(){
        return buyingService.list();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getOne/{id}")
    public String findStringValueById(@PathVariable Long id){
        return findBuyingById(id).toString();
    }

    private Buy findBuyingById(Long id){
        return buyingService.findById(id);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/get/livre/{idL}/customer/{idC}")
    public String findStringValueByBookAndCustomer(@PathVariable Long idL, @PathVariable Long idC){
        return findBuyingByBookAndCustomer(idL, idC).toString();
    }

    private Buy findBuyingByBookAndCustomer(Long idL, Long idC){
        return buyingService.findByBookAndCustomer(idL, idC);
    }

    /*@RequestMapping(method = RequestMethod.GET, path = "/getAll")
    public String findAllPagesStringValueByName(){

    }*/

    //pages, méthodes à implémenter :
    //- toutes les pages overall
    //- toutes les pages d'un livre
    //- toutes les pages du même number
    //- une page du livre et du number

    @RequestMapping(method = RequestMethod.PUT, path = "/livre/{idL}/customer/{idC}")
    public Buy add(@PathVariable Long idL, @PathVariable Long idC){
        Customer customer = new CustomerService().findById(idC);
        Set<Book> books = new HashSet<Book>(){{add(new BookService().findById(idL));}};
        return buyingService.add(new Buy(customer, books));
    }
}
