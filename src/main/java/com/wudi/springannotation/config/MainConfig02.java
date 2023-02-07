package com.wudi.springannotation.config;

import com.wudi.springannotation.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MainConfig02 {
    //默认都是单实例
    //prototype：多实例的:ioc容器启动并不会去调用方法创建对象放在容器中，
     //                    是在每次获取的时候才会调用方法创建对象
    // singleton ：单实例（默认值）：ioc容器启动会调用方法创建对象到ioc容器中，以后每次获取就直接从容器中获取
    // request：同一次请求创建一个实例 ，
    // session：同一个session 创建一个实例
    @Scope(value = "prototype")
    @Bean("person02")
    public Person beanPerson(){
        System.out.println("容器中添加Person-02");
      return   new Person("wudi",18);
    }
}
