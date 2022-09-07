package com.example.controllers.mapping;

import com.example.beans.Book;
import com.example.beans.Purchase;
import com.example.beans.Customer;
import com.example.services.BookService;
import com.example.services.PurchaseService;
import com.example.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/purchase")
public class PurchaseMappingController {

    private static final Logger log = LoggerFactory.getLogger(PurchaseMappingController.class);

    @Autowired
    private PurchaseService purchaseService;

    //@RequestMapping(method = RequestMethod.GET, path = "/list")
    @GetMapping(path = "/list")
    public List<Purchase> list(){
        return purchaseService.list();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getOne/{id}")
    public String findStringValueById(@PathVariable Long id){
        return findPurchaseById(id).toString();
    }

    private Purchase findPurchaseById(Long id){
        return purchaseService.findById(id);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/get/livre/{idL}/customer/{idC}")
    public String findStringValueByBookAndCustomer(@PathVariable Long idL, @PathVariable Long idC){
        return findPurchaseByBookAndCustomer(idL, idC).toString();
    }

    private Purchase findPurchaseByBookAndCustomer(Long idL, Long idC){
        return purchaseService.findByBookAndCustomer(idL, idC);
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
    public Purchase add(@PathVariable Long idL, @PathVariable Long idC){
        Customer customer = new CustomerService().findById(idC);
        Set<Book> books = new HashSet<Book>(){{add(new BookService().findById(idL));}};
        return purchaseService.add(new Purchase(customer, books));
    }
}
