package com.geek.spring.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/22
 */
@Configuration //表示可作为bean定义的来源类
@Import(JavaBeanSchConfig.class) //引入另一个来源配置文件 实例化上下文只需要实例此文件
public class JavaBeanStuConfig {


    @Bean(name="stu004",initMethod = "init",destroyMethod = "destory") //告诉spring 定义一个name为stu004的bean、初始化方法以及销毁方法
    @Scope("prototype")
    public Student getStudent() {
        return new Student(4l,"stu004",getKlass(),getSchool());
    }

    @Bean
    public Klass getKlass() {
        return new Klass(1,getSchool());
    }

    @Bean
    public School getSchool() {
        return new School("initSchoolName");
    }
}
