package com.geek.spring.bean;

import com.geek.spring.inf.ISchool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/19
 */

public class School implements ISchool {

    private String name;

    public School(String name) {
        this.name = name;
    }

    @Override
    public void study() {
        System.out.println("school study...");
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                '}';
    }
}
