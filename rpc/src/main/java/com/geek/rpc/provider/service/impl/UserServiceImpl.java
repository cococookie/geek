package com.geek.rpc.provider.service.impl;

import com.geek.rpc.proapi.UserService;
import com.geek.rpc.proapi.vo.User;
import org.springframework.stereotype.Service;

/**
 * @Author: siyan.liu
 * @Date: 2021/11/18
 */

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findById(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("Tracy");
        return user;
    }
}
