package com.example.controllers.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexController extends BasePageController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @Override
    public void doOnOpenJob(){
        displayBean(findAll());
        //addItem(new MyBeanA(IntUtils.getRandomInt(1, 10)));
    }

    @Override
    /*public void addItem(){
        BaseBeanController mbc = new BaseBeanController("ABSTRACTITEM_A");
        MyBeanA mb = new MyBeanA(IntUtils.getRandomInt(1, 10));
        mbc.save(mb);
        bean = mb;
    }*/

    public String displayString(){
        return defaultString("This is the index page, keep going. :|", bean);
    }
}