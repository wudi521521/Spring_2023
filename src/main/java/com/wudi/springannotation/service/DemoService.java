package com.wudi.springannotation.service;

import com.wudi.springannotation.config.DemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    @Autowired
    private DemoDao demoDao;

    public void print(){
        System.out.println(demoDao);
    }
}
