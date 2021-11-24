package com.geek.rpc.client;


import com.geek.rpc.proapi.UserService;
import com.geek.rpc.proapi.vo.User;

public class RpcfxClientApplication {


	public static void main(String[] args) {

		UserService userService = Rpcfx.create(UserService.class, "http://localhost:8081/rpc");
		User user = userService.findById(1l);
		System.out.println("find user id=1 from server: " + user.getName());

	}

}



