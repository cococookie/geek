package bean;

import java.util.List;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/19
 */
public class Klass {

    private List<Student> studentList;

    public void studyMath() {
        System.out.println("studyMath...");
    }

    @Override
    public String toString() {
        return "Klass{" +
                "studentList=" + studentList +
                '}';
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
