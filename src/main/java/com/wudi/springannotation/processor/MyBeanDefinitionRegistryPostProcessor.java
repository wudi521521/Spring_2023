package com.wudi.springannotation.processor;

import com.wudi.springannotation.bean.Yellow;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    //Registry Bean定义信息的保存中心，以后BeanFactory就是按照BeanDefinitionRegistry里面保存的每一个bean定义的信息
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("ConfigurableListableBeanFactory。。。bean的数量"+beanFactory.getBeanDefinitionCount());
        //new RootBeanDefinition(Yellow.class)；

    }

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanFactory) throws BeansException {
        System.out.println("postProcessBeanDefinitionRegistry。。。bean的数量"+beanFactory.getBeanDefinitionCount());
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(Yellow.class);
        beanFactory.registerBeanDefinition("hello",builder.getBeanDefinition());
    }
}
