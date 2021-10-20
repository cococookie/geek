package com.geek.study.spring.aop;

import com.geek.study.spring.bean.Student;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class HelloBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println(" ====> 处理属性赋值前： " + beanName  +":"+ bean);
        // 可以加点额外处理
        // 例如
        if (bean instanceof Student) {
            Student student = (Student) bean;
            student.setName(student.getName() + "-" + System.currentTimeMillis());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)  {
        System.out.println(" ====> 处理属性赋值后：  " + beanName +":"+ bean);
        return bean;
    }
}
