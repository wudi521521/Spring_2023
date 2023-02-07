package test;

import com.wudi.springannotation.config.MainConfig;
import com.wudi.springannotation.config.MainConfig02;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (int i = 0; i < beanDefinitionNames.length; i++) {
            System.out.println("======"+beanDefinitionNames[i]);

        }
    }

    @Test
    public void test02(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig02.class);
        System.out.println("ioc容器创建完成。。。。");

        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();

        for (int i = 0; i < beanDefinitionNames.length; i++) {
            System.out.println("======"+beanDefinitionNames[i]);

        }
        Object bean = annotationConfigApplicationContext.getBean("person02");
        Object bean02 = annotationConfigApplicationContext.getBean("person02");

    }
}
