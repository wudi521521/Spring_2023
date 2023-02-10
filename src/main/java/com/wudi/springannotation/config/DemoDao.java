package com.wudi.springannotation.config;

import org.springframework.stereotype.Repository;

@Repository
public class DemoDao {

    public void demo(){
        System.out.println("...demoDao");
    }
}
