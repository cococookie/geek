package com.geek.study.spring;

import com.geek.study.spring.bean.JavaConfig;
import com.geek.study.spring.bean.Klass;
import com.geek.study.spring.bean.Student;
import com.geek.study.spring.inf.ISchool;
import com.geek.study.utils.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/19
 */
public class SpringDemo extends BaseTest {

    @Autowired
    private ISchool school;
    @Autowired
    @Qualifier("stu004")
    private Student student;

    public static void main(String[] args) {

        /**
         * 1、xml文件根据ID获取bean
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student stu001 = (Student)context.getBean("student001");
        System.out.println(stu001);

        /**
         * 2、xml文件根据class获取bean
         * 如果同一个class配置了多个bean，则会报不唯一的异常
         */
        Klass klass = context.getBean(Klass.class);
        System.out.println(klass);

        /**
         * 3、javaConfig @Bean
         */
        ApplicationContext context1 = new AnnotationConfigApplicationContext(JavaConfig.class);
        Student stu004 = (Student)context1.getBean("stu004");
        System.out.println(stu004);

    }

    @Test
    public void demoTest() {
        /**
         * 4、注解注入
         */
        System.out.println(school);

        System.out.println(student);

    }

}
