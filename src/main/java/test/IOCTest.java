package test;

import com.wudi.springannotation.bean.Person;
import com.wudi.springannotation.config.MainConfig;
import com.wudi.springannotation.config.MainConfig02;
import com.wudi.springannotation.config.MainConfigOfLifeCycle;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

public class IOCTest {
    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig02.class);


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
           System.out.println("ioc容器创建完成。。。。");

        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();

        for (int i = 0; i < beanDefinitionNames.length; i++) {
            System.out.println("======"+beanDefinitionNames[i]);

        }
        Object bean = annotationConfigApplicationContext.getBean("person02");
        Object bean02 = annotationConfigApplicationContext.getBean("person02");
        Object bean03 = annotationConfigApplicationContext.getBean("person03");
        Object bean033 = annotationConfigApplicationContext.getBean("person03");
        Object colorFactoryBean = annotationConfigApplicationContext.getBean("colorFactoryBean");
        System.out.println(colorFactoryBean.getClass());

        //获取应用系统
        ConfigurableEnvironment contextEnvironment = annotationConfigApplicationContext.getEnvironment();
        System.out.println(contextEnvironment.getProperty("os.name"));


    }

    @Test
    public void test03(){
        //1：创建容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("-----容器创建完成------");
        context.getBean("car");
        context.getBean("car");
        context.getBean("car");
        //2：关闭容器
        context.close();
        System.out.println("----容器销毁完成-----");
    }
}
