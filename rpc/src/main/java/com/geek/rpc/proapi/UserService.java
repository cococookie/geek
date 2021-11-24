package com.geek.rpc.proapi;

import com.geek.rpc.proapi.vo.User;

/**
 * @Author: siyan.liu
 * @Date: 2021/11/18
 */
public interface UserService {

    User findById(Long id);

}
