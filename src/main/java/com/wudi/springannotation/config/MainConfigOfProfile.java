package com.wudi.springannotation.config;

import org.springframework.context.annotation.Configuration;

/**
 * Profile：
 *     Spring为我们提供的可以根据当前环境，动态的激活和切换一系列组件的功能。
 * 开发环境：测试环境，生产环境
 * @Profile：指定组件在哪个环境的情况下才能背注册到容器中，不指定，任何环境下都能注册这个组件
 * 1）加了环境标识bean，只有这个环境被激活时候才能注册到容器中，默认是default环境
 *    使用命令行动态参数：在虚拟机参数位置加载-Dspring.profiles.active=env(环境)
 * 2）代码激活profile
 *    创建一个ApplicationContext
 *    设置需要激活的环境：applicationContext.getEnvironment().setActiveProfiles("test","dev");
 *    注册主配置类：applicationContext.register(MainConfigOfProfile.class)
 *    启动刷新容器：applicationContext.refresh()
 * 3）@Profile注解，也可以写在类上。只有是指定的环境的时候，整个配置类里面的所有配置才能开始生效
 */
@Configuration
public class MainConfigOfProfile {
}
