package com.geek.study.spring;

import com.geek.study.spring.bean.Klass;
import com.geek.study.spring.bean.School;
import com.geek.study.spring.bean.Student;
import com.geek.study.spring.inf.ISchool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/19
 */
public class SpringDemo {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student stu001 = (Student)context.getBean("student001");
        System.out.println(stu001);

        Klass klass = context.getBean(Klass.class);
        System.out.println(klass);

        klass.studyMath();

        ISchool school = context.getBean(School.class);
        System.out.println(school);

        school.study();
    }
}
