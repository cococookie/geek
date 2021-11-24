package com.geek.dao.impl;


import com.geek.criteria.OrderCriteria;
import com.geek.dao.BaseDao;
import com.geek.dao.OrderDao;
import com.geek.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: siyan.liu
 * @Date: 2021/11/15
 */

@Repository
public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public List<OrderEntity> queryOrderList(OrderCriteria criteria) {
        return getSqlSession().selectList("orderMapper.selectOrder",criteria);
    }

    @Override
    public Integer save(OrderCriteria criteria) {
        return getSqlSession().insert("orderMapper.insertOrder",criteria);
    }
}
