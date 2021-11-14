package com.geek.study.sql;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.geek.study.sql.criteria.OrderCriteria;
import org.apache.ibatis.session.SqlSession;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config-dao2.xml");

    @Test
    public void test1() {
//        DynamicDataSourceSwitch.setDataSource("master");
        /* 得到 SqlSession 对象*/
        SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");

        // 调用 Mapper映射文件里的方法
        OrderCriteria criteria = new OrderCriteria();
        criteria.setOrderId(666684391162359808l);
        List list = sqlSession.selectList("orderMapper.selectOrder",criteria);
        System.out.println(JSONObject.toJSON(list));
    }

}
