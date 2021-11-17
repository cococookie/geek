package com.geek.study.sql.dao;

import com.geek.study.sql.criteria.OrderCriteria;
import com.geek.study.sql.entity.OrderEntity;

import java.util.List;

/**
 * @Author: siyan.liu
 * @Date: 2021/11/15
 */
public interface OrderDao {

    List<OrderEntity> queryOrderList(OrderCriteria criteria);

    Integer save(OrderCriteria criteria);
}
