package test;

import com.wudi.springannotation.config.ExConfig;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class Ex_AOP {

    @Bean
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExConfig.class);
        context.publishEvent(new ApplicationEvent(new String("发布一个事件")) {
            @Override
            public Object getSource() {
                return super.getSource();
            }
        });

    }
}
