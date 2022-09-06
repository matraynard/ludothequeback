package com.example.old;

import com.example.old.page.IndexController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController {

    private static final Logger log = LoggerFactory.getLogger(MappingController.class);

    @RequestMapping("/error")
    public String errorPage(){
        return "This is the error page, no luck. :(";
    }

    @RequestMapping("/hello")
    public String helloPage(){
        return "Hello World. This is a cool page, good luck. :)";
    }

    @RequestMapping("/")
    public String defaultPage(){
        IndexController ic = new IndexController();
        return ic.onOpen() + "This is the index page, keep going. :|";
    }

    /*@GetMapping("/error")
    public void errorPage(){
        System.out.println("Hello World. This is the error page, no luck :(");
    }

    @GetMapping("/hello")
    public void helloPage(){
        System.out.println("Hello World. This is a cool page, good luck :)");
    }

    @GetMapping("/")
    public void index() {
        System.out.println("Greetings from Spring Boot!");
    }*/

    /*@GetMapping("/")
    public void defaultPage(){
        System.out.println("Hello World. This is the default page, keep going.");
    }*/
}