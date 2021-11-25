package com.geek.spring.bean;

import java.util.List;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/19
 */
public class Klass {

    private Integer id;

    private List<Student> studentList;

    private School school;

    public void studyMath() {
        System.out.println("studyMath...");
    }

    public Klass(Integer id, School school) {
        this.id = id;
        this.school = school;
    }

    public Klass() {
    }

    public Klass(Integer id, List<Student> studentList, School school) {
        this.id = id;
        this.studentList = studentList;
        this.school = school;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Klass{" +
                "id=" + id +
                ", studentList=" + studentList +
                ", school=" + school +
                '}';
    }
}
