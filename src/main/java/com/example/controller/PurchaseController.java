package com.example.controller;

import com.example.entity.Purchase;
import com.example.services.PurchaseService;
import com.example.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5173/")
@RequestMapping("/purchase/")
public class PurchaseController {

    private static final Logger log = LoggerFactory.getLogger(PurchaseController.class);

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private CustomerService customerService;

    @DeleteMapping(path = "{id}")
    public String delete(@PathVariable Long id){
        Purchase purchase = purchaseService.delete(id);
        return "L'achat passé de " + purchase.getArticles() + " par " + purchase.getCustomer().getName() + " le " + purchase.getPurchaseCreationDate() + " a été supprimé.";
    }

    @GetMapping(path = "list")
    public List<Purchase> findAll(){
        return purchaseService.findAll();
    }

    @GetMapping(path = "{id}")
    public Purchase findById(@PathVariable Long id){
        return purchaseService.findById(id);
    }

    @GetMapping(path = "date/{date}")
    public List<Purchase> findByDate(@PathVariable String date){
        LocalDate lDate = LocalDate.parse(date);
        Instant i = lDate.atStartOfDay(ZoneId.of("Europe/Paris")).toInstant();
        return purchaseService.findByCreationDate(i);
    }

    @GetMapping(path = "hello")
    public String hello(){
        return "C'est la page de hello world. Retourne travailler maintenant.";
    }

    /** create a new purchase date, empty, with no book linked to it, it's equivalent to a basket */
    @PostMapping(path = "customer/{customerId}/date/{date}")
    public Purchase createNewBasket(@PathVariable Long customerId, @PathVariable String date){
        LocalDate lDate = LocalDate.parse(date);
        Instant i = lDate.atStartOfDay(ZoneId.of("Europe/Paris")).toInstant();
        Purchase purchase = new Purchase(customerService.findById(customerId), i);
        purchaseService.ceateNewbasket(purchase);
        return purchase;
    }

    /** set the customer of the purchase */
    @PutMapping(path = "{purchaseId}/{customerId}")
    public Purchase setCustomer(@PathVariable Long purchaseId, @PathVariable Long customerId){
        return purchaseService.setCustomer(purchaseId, customerId);
    }
}
