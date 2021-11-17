package com.geek.study.sql.dao.impl;

import com.geek.study.sql.criteria.OrderCriteria;
import com.geek.study.sql.dao.BaseDao;
import com.geek.study.sql.dao.OrderDao;
import com.geek.study.sql.entity.OrderEntity;
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
