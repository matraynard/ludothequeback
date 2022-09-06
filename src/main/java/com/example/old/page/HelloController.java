package com.example.old.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloController extends BasePageController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @Override
    public void doOnOpenJob(){
        //addItem(new Page(new Book(), IntUtils.getRandomInt(1, 10)));
    }

    public String displayString(){
        return defaultString("This is a cool page, good luck. :)", bean);
    }
}