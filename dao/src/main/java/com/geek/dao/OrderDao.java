package com.geek.dao;



import com.geek.criteria.OrderCriteria;
import com.geek.entity.OrderEntity;

import java.util.List;

/**
 * @Author: siyan.liu
 * @Date: 2021/11/15
 */
public interface OrderDao {

    List<OrderEntity> queryOrderList(OrderCriteria criteria);

    Integer save(OrderCriteria criteria);
}
