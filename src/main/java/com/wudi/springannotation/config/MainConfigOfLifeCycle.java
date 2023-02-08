package com.wudi.springannotation.config;

import com.wudi.springannotation.bean.Car;
import com.wudi.springannotation.bean.Cat;
import com.wudi.springannotation.bean.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * bean的生命周期：
 *      bean创建---初始化---销毁的过程
 * 容器管理bean的生命周期：
 * 我们可以自定义初始化和销毁方法；容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
 *
 * 构建（创建对象）
 *     单实例：IOC容器创建时，创建对象
 *     多实例：IOC创建完成，获取bean时创建对象
 * 初始化
 *     对象创建完成，并赋值好，调用初始化方法
 * 销毁
 *     单实例：容器关闭时，销毁
 *     多实例：容器不会管理这个Bean
 * 遍历得到容器所有的BeanPostProcessor；
 * 挨个执行beforeInitialization
 * 一但返回null，跳出for循环，不会执行后面的BeanPostProcessor
 * populateBean（beanName,mbd,instanceWrapper）{
 *
 * }
 * 1）指定初始化和销毁方法
 *    通过@Bean指定：init-method=""  销毁：destroy-method=""
 * 2）通过@Bean实现InitalizaingBean（定义初始化逻辑），DisposableBean（定义销毁逻辑）
 * 3）可以使用JSR250
 *    @PostConstruct：在bean创建完成并且属性赋值完成；来执行初始化方法
 *    @PreDestroy:在容器销毁bean之前通知我们进行清理工作
 * 4) BeanPostProcessor原理
 * populateBean（beanName,mbd,instanceWrapper）；给bean进行属性赋值
 *    在bean初始化前后进行一些处理工作
 *    postProcessorBeforeInitialization；
 *    postProcessorAfterInitialization；
 *
 *  Spring 底层对BeanPostProcessor使用
 *     bean赋值，注入其他组件，@Autowired 生命周期注解功能，@Async，xxx 都是通过BeanPostProcessor
 */
@Configuration
public class MainConfigOfLifeCycle {

    @Scope(value = "prototype")
    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car(){
        return new Car();
    }

    @Bean
    public Cat cat(){
        return new Cat();
    }

    @Bean
    public Dog dog(){
        return new Dog();
    }
}
