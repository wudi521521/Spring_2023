package com.wudi.springannotation.config;


import com.wudi.springannotation.processor.BeanFactoryPostProcessor01;
import com.wudi.springannotation.processor.MyApplicationListener;
import com.wudi.springannotation.processor.MyBeanDefinitionRegistryPostProcessor;
import com.wudi.springannotation.processor.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 扩展原理：
 * BeanPostProcessor：bean后置处理器，bean创建对象初始化前后进行拦截工作的
 * 1，BeanFactoryPostProcessor：beanFactory的后置处理器
 *      在BeanFactory标准初始化之后调用，所有的Bean定义已经保存加载到BeanFactory，但是
 *      Bean的实例还未创建。
 *  1)IOC容器创建对象
 *  2）invokeBeanFactoryPostProcessors(beanFactory);执行BeanFactoryPostProcessor
 *  如何找到所有BeanFactoryPostProcessor并执行他们的方法
 *        1）直接在BeanFactory中找到所有类型是BeanFactoryPostProcessor的组件，并执行他们的方法
 *        2）在初始化创建其他组件前面执行
 * 2，BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 *        postProcessBeanDefinitionRegistry（）；
 *        在所有bean定义信息将要背加载，bean实例还未被创建的，
 *
 *        优先于BeanFactoryPostProcessor执行，
 *        利用BeanDefinitionRegistryPostprocessor给容器添加一些组件。
 *原理：
 *    1）ioc创建对象
 *    2）refresh（）-》invokeBeanFactoryPostProcessor（BeanFactory）
 *    3）从容器中获取到所有的BeanDefinitionRegistryPostProcessor组件，依次触发所有的
 *        1；postProcessBeanDefinitionRegister（）方法
 *        2：再来触发postProcessBeanFactory（）方法BeanFactoryPostProcessor
 *    4）再从容器中找到BeanFactoryPostProcessor组件，然后依次触发postProcessBeanFactory（）方法
 *
 * 3.ApplicationListener 监听容器中发布的事件。事件驱动模型开发
 *    public interface ApplicationListener<E extends ApplicationEvent>
 *    监听ApplicationEvent及其下面的子事件
 *    步骤：
 *    1）写一个监听器来监听某个事件（ApplicationEvent及其子类）
 *       或者使用注解@EventListener 作用在方法上
 *
 *    2）把监听器加入到容器；
 *    3）只要容器中有相关事件的发布，我们就能监听到这个事件；
 *            ContextRefreshedEvent：容器刷新完成（所有bean完全创建完成）就会发布这个事件
 *            ContextClosedEvent：关闭容器就会发布这个事件
 *    4）发布一个事件
 *      1）容器创建对象：refresh（）
 *      2）finishRefresh（）；容器刷新完成
 *      3）publishEvent(new ContextRefreshedEvent(this));
 *            事件发布流程：
 *                1）获取事件的多播器（派发器）：getApplicationEventMulticaster（）
 *                2）multicastEvent派发事件：
 *                3）获取到所有的ApplicationListener
 *        【事件派发器（派发器）】
 *        【容器中有哪些监听器】
 *
 */
@Import({BeanFactoryPostProcessor01.class,MyBeanDefinitionRegistryPostProcessor.class,
        MyApplicationListener.class, UserService.class})
@Configuration
public class ExConfig {
}
