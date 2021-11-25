package com.geek.spring.aop;

import com.geek.spring.bean.Student;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 自动方法前后处理
 */

public class HelloBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println(" ====> 处理属性赋值前： " + beanName  +":"+ bean);
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
