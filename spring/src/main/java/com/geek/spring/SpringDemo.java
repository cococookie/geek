package com.geek.spring;

import com.geek.spring.bean.Student;
import com.geek.spring.inf.ISchool;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utils.BaseTest;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/19
 */
public class SpringDemo extends BaseTest {

    @Autowired
    private ISchool school;
    @Autowired
    @Qualifier("stu004") //指定具体的引入
    private Student student;

    public static void main(String[] args) {

        /**
         * 1、xml文件根据ID获取bean
         */
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-model-config.xml");
        Student stu001 = (Student)context.getBean("stu004");
        System.out.println(stu001);

    }

    @Test
    public void demoTest() {
        System.out.println(school);
        System.out.println(student);
    }

}
