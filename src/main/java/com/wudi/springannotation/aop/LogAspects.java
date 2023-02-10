package com.wudi.springannotation.aop;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 切面类
 * @Aspect：告诉Spring这是一个切面类
 */
@Aspect
public class LogAspects {

    /**
     * 抽取公共的切入点表达式
     * 1：本类引用
     * 2：其他的切面引用
     */
    @Pointcut("execution(public int com.wudi.springannotation.aop.MathCalculator.*(..))")
    public void pointCut(){}

    //@Before在目标方法切入前切入，切入点表达式（指定在哪个方法切入）
    @Before("pointCut()")
    public void logStart(){
        System.out.println("除法运算。。。。参数列表是:@Before");
    }

    //可以获取方法名，参数
    @After(value = "pointCut()")
    public void logEnd(JoinPoint joinPoint){
        //方法名
        joinPoint.getSignature().getName();
        //参数
        Object[] objects = joinPoint.getArgs();
        System.out.println("除法运行结束。。。参数列表是:@After"+joinPoint.getSignature().getName()+joinPoint.getArgs());
    }

    //含有返回结果,JoinPoint必须放置在第一位
    @AfterReturning(value = "pointCut()",returning="result")
    public void logReturn(JoinPoint joinPoint,Object result){
        System.out.println("除法正常返回。。。。运行结果:@AfterReturning====="+result);
    }

    //获取异常
    @AfterThrowing(value ="pointCut()",throwing = "exception")
    public void logException(JoinPoint joinPoint,Exception exception){
        System.out.println("除法异常。。。。运行结果:@AfterThrowing======"+exception);
    }

//    @Around("pointCut()")
//    public void logAround(){
//        System.out.println("除法环绕。。。。运行结果:@Around");
//    }
}
