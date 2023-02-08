package com.wudi.springannotation.bean;

import org.springframework.beans.factory.FactoryBean;

//创建一个Spring定义的FactoryBean
public class ColorFactoryBean implements FactoryBean<Red> {
    //返回一个color对象，这个对象会添加到容器中
    public Red getObject() throws Exception {
        return new Red();
    }

    public Class<?> getObjectType() {
        return Red.class;
    }

    //是单例？
    //true：这个bean是单例，在这个容器中保存一份
    public boolean isSingleton() {
        return false;
    }
}
