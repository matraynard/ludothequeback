package com.example.controllers.page;

import com.example.controllers.BaseBeanController;
import com.example.controllers.PageController;
import com.example.interfaces.IBaseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class BasePageController<T extends IBaseBean> {

    private static final Logger log = LoggerFactory.getLogger(BasePageController.class);

    public T bean;

    //public BaseBean bean;

    public String onOpen(){
        doOnOpenJob();
        return displayString();
    }

    abstract void doOnOpenJob();

    public void addItem(T bean){
        BaseBeanController mbc = new BaseBeanController(bean);
        mbc.save(bean);

        //T mb = new MyBeanA(IntUtils.getRandomInt(1, 10));
        //bean = mb;
    }

    public String displayBean(List<T> beansList){
        return "";
    }

    public List<T> findAll(){
        PageController mbbc = new PageController("livre");
        return (List<T>) mbbc.findAll();
    }

    public void removeItem(T bean){
        BaseBeanController mbc = new BaseBeanController(bean);
        mbc.deleteById(bean.getId(), bean.getClass());
    }

    //abstract void doSomethingWithItem();

    abstract String displayString();

    public String defaultString(String str, T bean){
        StringBuilder sb = new StringBuilder();
        sb.append(str + "\n");
        if (bean != null){
            sb.append(bean);
        }

        return sb.toString();
    }
}