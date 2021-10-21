package com.geek.study.spring.bean;

import com.geek.study.spring.inf.ISchool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/19
 */
@Component
public class School implements ISchool {

    @Autowired(required = true)
    private Klass klass;

    @Resource(name="student001")
    private Student student;

    @Override
    public String toString() {
        return "School{" +
                "klass=" + klass +
                ", student=" + student +
                '}';
    }

    @Override
    public void study() {
        System.out.println("school study...");
    }

}
