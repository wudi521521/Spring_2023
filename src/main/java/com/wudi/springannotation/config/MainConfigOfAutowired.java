package com.wudi.springannotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配：
 *     Spring利用依赖注入（DI），完成IOC容器中各个组件的依赖关系赋值。
 * 1) @Autowired:自动注入【spring规范的注解】
 *            1)默认优先按照类型去容器中找对应的组件：applicationContext.getBean(BookDao.class);
 *  *         2)如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找
 *                  applicationContext.getBean("bookDao")
 *            3)@Qualifier("bookDao");s使用@Qualifier指定需要装配的组件的id，而不是使用属性名称。
 *            4）自动装配默认一定要将属性赋值好，IOC容器中没有，就会报错；
 *                可以使用@autowired（required = false），IOC容器中没有不会报错
 *            5）@Primary；让Spring进行自动装配的时候，默认使用首先的bean
 *                也可以使用@Quqlifier指定组件
 *  2）Spring还支持使用@Resource（JSR250）和@Inject（JSR330）【java规范的注解】
 *            @Resource：
 *            可以和@Autowired一样实现自动装配功能，默认时按照组件名称进行装配的，
 *            没有能支持@Priamry，@autowired（required = false）功能
 *            @Inject：
 *            需要导入javax.inject的包，和Autowired的功能一样，没有required=false的功能
 *     BookService{
 *
 *                       @Autowired
 *                       BookDao bookDao；
 *     }
 *     AutowiredAnnotationBeanPostProcessor：解析完成自动装配功能
 *  3）@Autowired能作用在属性，方法，构造器，属性；都是从IOC容器中赋值
 *          1）标注在方法上，Spring容器创建当前对象，就会调用方法，完成赋值
 *             方法使用的参数，自定义类型的值从ioc容器中获取
 *          2）标注在构造器方法，【默认加载IOC容器中的组件，容器启动会调用无参构造器创建对象，再进行初始化赋值等操作】
 *             标注在构造器要用的组件，从IOC容器中获取
 *             如果组件只有一个有参构造器，这个参数构造器的@Autowied可以省略，参数位置的组件还是可以从容器中获取。
 *  4）自定义组件想要使用Spring容器底层的一些组件（ApplicationContext，BeanFactory，xxx）
 *         自定义组件实现xxxAware；在创建对象的时候，会调用接口规定的方法注入相关组件；Aware
 *         ApplicationContextAware ==》ApplicationContextAwareProcessor
 */
@ComponentScan(value = "com.wudi")
@Configuration
public class MainConfigOfAutowired {

}
