package test;

import com.wudi.springannotation.config.MainConfigOfAutowired;
import com.wudi.springannotation.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOC_AUTOTest {

    @Test
    public void autoTest01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        DemoService bean = applicationContext.getBean(DemoService.class);
        bean.print();
    }
}
