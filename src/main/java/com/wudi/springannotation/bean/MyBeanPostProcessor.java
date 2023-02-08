package com.wudi.springannotation.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 后置处理器前后
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    //初始化之前
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("》》》》postProcessor before");
        return o;
    }

    //初始化之后
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("》》》》postProcessor after");
        return o;
    }
}
