package com.wudi.springannotation.config;

import com.wudi.springannotation.bean.ColorFactoryBean;
import com.wudi.springannotation.bean.Person;
import com.wudi.springannotation.bean.Red;
import com.wudi.springannotation.condition.MacCondition;
import org.springframework.context.annotation.*;

import java.io.Reader;
@Import({Red.class,MyImportSelector.class})
@Configuration
public class MainConfig02 {
    //默认都是单实例
    //prototype：多实例的:ioc容器启动并不会去调用方法创建对象放在容器中，
     //                    是在每次获取的时候才会调用方法创建对象
    // singleton ：单实例（默认值）：ioc容器启动会调用方法创建对象到ioc容器中，以后每次获取就直接从容器中获取
    // request：同一次请求创建一个实例 ，
    // session：同一个session 创建一个实例

    /**
     *
     * 懒加载：
     *      单实例bean：默认在容器启动的时候创建对象，
     *      懒加载：容器启动不创建对象，第一使用（获取）Bean创建对象，并初始化
     */
    @Lazy
    @Bean("person02")
    public Person beanPerson(){
        System.out.println("容器中添加Person-02");
      return   new Person("wudi",18);
    }


    @Scope(value = "prototype")
    @Bean("person03")
    public Person person03(){
        System.out.println("容器中添加Person-03");
        return new Person("bill gates",62);
    }

    @Conditional({MacCondition.class})
    @Bean("person04")
    public Person person04(){
        System.out.println("容器中添加person-04");
        return new Person("linu",32);
    }

    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }
}
