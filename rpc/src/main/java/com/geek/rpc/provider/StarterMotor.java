package com.geek.rpc.provider;

import com.geek.rpc.req.RpcfxRequest;
import com.geek.rpc.resp.RpcfxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: siyan.liu
 * @Date: 2021/11/18
 */
@Controller
@RequestMapping("/")
public class StarterMotor {

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "index";
    }

    @Autowired
    RpcfxInvoker invoker;

    @RequestMapping(value = "/rpc",method = RequestMethod.POST)
    @ResponseBody
    public RpcfxResponse invoke(@RequestBody RpcfxRequest request) {
        return invoker.invoke(request);
    }


}
