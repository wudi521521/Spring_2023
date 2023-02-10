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
 *
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
