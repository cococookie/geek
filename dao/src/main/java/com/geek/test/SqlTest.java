package com.geek.test;

import com.alibaba.fastjson.JSON;
import com.geek.criteria.OrderCriteria;
import com.geek.dao.OrderDao;
import com.geek.entity.OrderEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import utils.BaseTest;

import java.util.Date;
import java.util.List;

/**
 * @Author: siyan.liu
 * @Date: 2021/11/7
 */
public class SqlTest extends BaseTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void queryOrderList() {
        OrderCriteria criteria = new OrderCriteria();
        criteria.setOrderId(666684391162359808l);
        List<OrderEntity> entityList = orderDao.queryOrderList(criteria);
        System.out.println(JSON.toJSON(entityList));
    }

    @Test
    public void addOrder() {
        Date now = new Date();
        OrderCriteria criteria = new OrderCriteria();
        criteria.setOrderId(202111150001l);
        criteria.setUserId(1l);
        criteria.setVendorId("1");
        criteria.setStoreId("12");
        criteria.setPhone("1381234567");
        criteria.setAddr("北京市");
        criteria.setPrice(5000l);
        criteria.setUpdateTime(now);
        criteria.setCreateTime(now);
        criteria.setUpdateUser("admin");
        Integer count = orderDao.save(criteria);
        System.out.println(count);
    }

}
