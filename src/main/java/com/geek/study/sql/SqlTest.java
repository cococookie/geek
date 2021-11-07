package com.geek.study.sql;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Author: siyan.liu
 * @Date: 2021/11/7
 */
public class SqlTest {
    /*
    * 1. 创建spring容器
           根据xml文件应用程序Context容器(上下文)
           classpath指配置文件的位置, 起点有java, resources. 写路径时相对这个起点去写
    * */
    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config-dao.xml");

    @Test
    public void test1() {
//        DynamicDataSourceSwitch.setDataSource("master");
        /* 得到 SqlSession 对象*/
        SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");

        // 调用 Mapper映射文件里的方法
        List list = sqlSession.selectList("t1Mapper.selectT1");
        System.out.println(list);
    }

}
