package com.example.old.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorController extends BasePageController {

    private static final Logger log = LoggerFactory.getLogger(ErrorController.class);

    @Override
    public void doOnOpenJob(){
        //addItem(new Customer("Customer error"));
    }

    public String displayString(){
        return defaultString("This is the error page, no luck. :(", bean);
    }
}