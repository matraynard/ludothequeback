package com.example.controller;

import com.example.entity.Rnumber;
import com.example.services.RnumberService;
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
@RequestMapping("/rnumber/")
public class RnumberController {

    private static final Logger log = LoggerFactory.getLogger(RnumberController.class);

    @Autowired
    private RnumberService rnumberService;

    /* -- DeleteMapping --

    @DeleteMapping(path = "{id}")
    public String delete(@PathVariable Long id){
        try{
            Game game = gameService.delete(id);
            return "Le livre " + game.getTitle() + " a été supprimé. Ses pages aussi :'(";
        }
        catch(Error e){

        }
        return "Une erreur s'est produite, le livre n'a pas pu être supprimé.";
    }*/

    /* -- GetMapping --*/

    /*@GetMapping(path = "list")
    public List<Rnumber> findAll(){
        return rnumberService.findAll();
    }*/

    @GetMapping(path = "{id}")
    public Rnumber findById(@PathVariable Long id) throws IOException {
        Rnumber game = rnumberService.findById(id);
        try{
            ObjectMapper om = new ObjectMapper();
            om.writeValue(new File("target/game.json"), game); //also works with .txt

            String filename = ZonedDateTime.now(ZoneId.of("Europe/Paris"))
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    + " BookController findById" + ".json";
            om.writeValue(new File("target/" + filename.trim()), game); //this one never work, idk why
        }
        catch(IOException ie){}

        return rnumberService.findById(id);
    }/*

    @GetMapping(path = "t/{title}")
    public List<Game> findByTitle(@PathVariable String title){
        return gameService.findByTitle(title);
    }

    @GetMapping(path = "hello")
    public String hello(){
        return "C'est la page de hello world. Retourne travailler maintenant.";
    }*/

    /* -- PostMapping --

    @PostMapping(path = "{title}/{price}")
    public Game save(@PathVariable String title, @PathVariable int price){
        Game game = null;
        if (title != null && !title.isEmpty() && price > 0){
            game = new Game(title, price);
            gameService.add(game);
        }
        return game;
    }*/

    /* -- PutMapping --

    @PutMapping(path = "{id}/{newTitle}")
    public Game update(@PathVariable Long id, @PathVariable String newTitle){
        return gameService.update(id, newTitle);
    }*/
}