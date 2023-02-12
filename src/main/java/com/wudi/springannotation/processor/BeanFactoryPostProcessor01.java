package com.wudi.springannotation.processor;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BeanFactoryPostProcessor01 implements BeanFactoryPostProcessor {


    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor.......");
        //定义的bean总数（但是并未实例化）
        int count = configurableListableBeanFactory.getBeanDefinitionCount();
        String[] names = configurableListableBeanFactory.getBeanDefinitionNames();
        System.out.println("当前BeanFactory中有"+count+"bean");
        System.out.println(Arrays.asList(names));

    }
}
