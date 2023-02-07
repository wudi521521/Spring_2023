package com.wudi.springannotation;

import com.wudi.springannotation.bean.Person;
import com.wudi.springannotation.config.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
    public static void main(String[] args) {
        //01 通过【配置文件】获取数据
        ApplicationContext pathXmlApplicationContext
                = new ClassPathXmlApplicationContext("beans.xml");
        Person person = (Person) pathXmlApplicationContext.getBean("person");
        System.out.println(person.toString());
        //02 通过【配置类】获取文件
        ApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person01 = (Person)annotationConfigApplicationContext.getBean("person01");
        System.out.println(person01);



    }
}
