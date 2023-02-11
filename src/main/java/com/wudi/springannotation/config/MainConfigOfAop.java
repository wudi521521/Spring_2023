package com.wudi.springannotation.config;

import com.wudi.springannotation.aop.LogAspects;
import com.wudi.springannotation.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP:
 *    指定程序运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程方式；
 * 1，导入aop模块，Spring aop:(spring-aspects)
 * 2，定义一个业务逻辑类（MathCalculator）
 * 3，定义日志切面类（LogAspects）：切面类里面的方法需要动态感知div运行行为
 *         通知方法：
 *            前置通知（@Before）
 *            后置通知（@After）
 *            返回通知（@AfterReturning）
 *            异常通知（@AfterThrowing）
 *            环绕通知（@Around）
 * 4, 给切面类的目标方法标注何时何地运行（通知注解）
 * 5,将切面类和业务逻辑类（目标方法所在类），都加入容器中
 * 6，必须告诉Spring哪个类是切面类（给切面类加一个注解，@Aspect）
 * 7，给配置类中加@EnableAspectJAutoProxy【开启基于注解的aop模式】
 *       在Spring中很多的Enable开头的注解
 *
 * 三步：
 *     1）将业务逻辑组件和切面类都加入到容器中，告诉Spring哪个是切面类（@Aspect）
 *     2）在切面类上的每一个通知方法上标注通知注解，告诉Spring何时何地进行（切入点表达式）
 *     3）开启基于注解的aop模式
 *AOP原理：
 * @EanableAspectJAutoProxy
 * 1，@EnableAspectJAutoProxy是什么
 *    导入@Import({AspectJAutoProxyRegistrar.class})：给容器中导入AspectJAutowired
 *    利用AspectJAutoProxyRegistrar自定义给容器中注册bean
 *    internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator；
 *    给容器中注册一个AnnotationAwareAspectJAutoProxyCreator
 * 2，AnnotationAwareAspectJAutoProxyCreator：
 *    -≥AspectJAwareAdvisorAutoProxyCreator
 *    -≥AbstractAdvisorAutoProxyCreator
 *    -》AbstractAutoProxyCreator implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *    关注后置处理器（在bean初始化完成前后做事情），自动装配BeanFactory
 * 3.创建internalAutoProxyCreator的BeanPostProcessor
 *    1）创建Bean的实例
 *    2）populateBean：给Bean的各种属性赋值
 *    3）initializeBean：初始化bean
 *    7把BeanPostProcessor注册到BeanFactory中
 *    beanFactory.addBeanPostProcessor(postProcessor)
 * =========以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程======
 * 1）每一个Bean创建之前，调用postProcessBeforeInstantiation（）
 *    关心MathCalculator和LogAspect的创建
 *    1）判断当前bean是否在advisedBeans中（保存了所有需要增强bean）
 *    2）判断当前bean是否是基础类型Advice，Pointcut，Advisor，AopInfrastrucureBean
 *    或者是否是切面（@Aspect）
 *    3）是否需要跳过
 *       1）获取候选的增强器（切面里面的通知方法）【List<Advisor> candidateAdvisor】
 *       每一个封装的通知方法的增强器是InstantiationModeAwarePointAdvisor；
 *       判断每一个增强器是否AspectJPointcutAdvisor类型；返回true
 *       2）永远返回false
 * 2）创建对象
 * postProcessAfterInitialization;
 *      return wrapIfNecessary(bean,beanName,cacheKey)//包装如果需要的情况下
 *     1。 获取当前bean的所有增强器（通知方法）Object【】 specificInterceptors
 *      1）获取当前bean的所有增强器（找哪些通知方法是需要切入当前bean方法的）
 *      2）获取到能在bean使用的增强器
 *      3）给增强器排序
 *    2。保存当前bean在adviseBean中
 *    3。如果当前bean需要增强，创建当前bean的代理对象
 *      1）获取所有增强器（通知方法）
 *      2）保存到proxyFactory
 *      3）创建代理对象：Spring自动决定
 *          JDKDynamicAOPProxy（config）；jdk动态代理
 *          ObjenesisCglibAopProxy（config）；cglib的动态代理
 *    4。给容器中返回当前组件使用cglib增强了的代理对象
 *    5。以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程。
 * 3）目标方法执行：
 *    容器中保存了组件的代理对象（cglib增强后的对象），这个对象里面保存了详细信息（比如增强器，目标对象，XXX）
 *    1）CglibAopProxy.intercept();拦截目标
 *    2）根据ProxyFactory对象获取将要执行的目标方法拦截器链
 *       1)List<Object> interceptorList保存所有拦截器5
 *       一个默认的ExposeInvocationInterceptor和4个增强器
 *       2)遍历所有的增强器，将其转为Interceptor
 *       registry.getInterceptors(advisor)
 *       3)将增强器转化为List<MethodInterceptor>
 *         如果是MethodInterceptor，直接加入到集合中
 *         如果不是，使用AdvisorAdapter将增强器转化为MethodInterceptor
 *         转换完成返回MethodInterceptor数组。
 *    3）如果没有拦截起链，直接执行目标方法
 *    4）如果有拦截器链，把需要执行的目标对象，目标方法，拦截器链等信息传入创建一个CglibMethodInvocation对象
 *    调用Obejct retVal = im.process()方法
 *    5）拦截器链的触发过程
 *      1）如果没有烂机器执行目标方法，或者拦截器的索引和拦截器数组-1大小一样（指定到了最后一个拦截器）执行目标方法
 *      2）链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器执行完成返回以后再来执行
 *      拦截器链的机制，保证通知方法与目标方法的执行顺序。
 *  总结：
 *    1）@EnableAspectJAutoProxy开启AOP功能
 *    2）@EnableAspectJAutoProxy会给容器中注册一个组件AnnotationAwareAspect
 *    3）AnnotationAwareAspectJAutoProxyCreator是一个后置处理器
 *    4）容器的创建流程
 *       1）registerBeanPostProcessor（）注册后置处理器
 *       2）finishBeanFactoryInitialization（）初始化剩下的单例bean
 *          1）创建业务逻辑组件和切面组件
 *          2）AnnotationAwareAspectJAutoProxyCreator拦截组件的创建过程
 *          3）组件创建完之后，判断组件是否需要增强
 *           是：切面的通知方法，包装成增强器（Advisor）；给业务逻辑组件创建一个代理对象
 *    5）执行目标方法：
 *      1）代理对象执行目标方法
 *      2）CglibAopProxy.intercept();
 *          1)得到目标方法的拦截器链（增强器包装成拦截器methodInterceptor）
 *          2）利用拦截器的链式机制，依次进入每一个拦截器进行执行
 *          3）效果：
 *            正常执行：前置通知-》目标方法-》后置通知-》返回通知
 *            出现异常：前置通知-》目标方法-》后置通知-》异常通知
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAop {

    //业务逻辑类加入到容器中
    @Bean
    public MathCalculator mathCalculator(){
        System.out.println("=========");
        return new MathCalculator();
    }

    //切面类加入到容器中
    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }
}
