package test;

import com.wudi.springannotation.aop.MathCalculator;
import com.wudi.springannotation.config.MainConfigOfAop;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_AOP {

    @Test
    public void test(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAop.class);
        MathCalculator contextBean = applicationContext.getBean(MathCalculator.class);
        contextBean.div(1,0);
        applicationContext.close();
    }
}
