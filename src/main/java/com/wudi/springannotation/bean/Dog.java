package com.wudi.springannotation.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Dog {


    public Dog(){
        System.out.println("dong >>> construction");
    }

    //对象创建并赋值之后调用
    @PostConstruct
    public void init(){
        System.out.println("dog >>>> init");
    }

    //容器移除对象之前
    @PreDestroy
    public void destroy(){
        System.out.println("dog 》》》 destroy");
    }
}
