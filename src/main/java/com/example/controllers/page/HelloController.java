package com.example.controllers.page;

import com.example.beans.Livre;
import com.example.beans.Page;
import com.example.utils.IntUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class HelloController extends BasePageController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @Override
    public void doOnOpenJob(){
        addItem(new Page(new Livre(), IntUtils.getRandomInt(1, 10))); //TODO corriger ici : chercher un livre déjà existant plutôt qu'en générer un nouveau
    }

    public String displayString(){
        return defaultString("This is a cool page, good luck. :)", bean);
    }
}