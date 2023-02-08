package com.wudi.springannotation.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

//自定义逻辑返回需要导入的组件
public class MyImportSelector  implements ImportSelector {
    //返回值，就是到导入到容器中的组件全类名
    //AnnotationMetadata：当前标注@Import注解的类型所有注解信息
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.wudi.springannotation.bean.Yellow"};
    }
}
