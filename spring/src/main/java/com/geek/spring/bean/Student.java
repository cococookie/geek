package com.geek.spring.bean;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/19
 */

public class Student implements Serializable{

    private static final long serialVersionUID = 8655583967440560916L;

    private Long id;

    private String name;

    private Klass klass;

    private School school;

    public Student() {
    }

    public Student(Long id, String name, Klass klass, School school) {
        this.id = id;
        this.name = name;
        this.klass = klass;
        this.school = school;
    }

    public void init(){
        System.out.println("init");
    }

    public void destory() {
        System.out.println("destory");
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", klass=" + klass +
                ", school=" + school +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }
}
