package com.geek.rpc.client;

import client.HttpClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.geek.rpc.req.RpcfxRequest;
import com.geek.rpc.resp.RpcfxResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: siyan.liu
 * @Date: 2021/11/17
 */
public class Rpcfx {

    //添加白名单 否则转换对象时会报autoType is not support
    static {
        ParserConfig.getGlobalInstance().addAccept("com.geek");
    }


    public static <T> T create(final Class<T> serviceClass, final String url) {
        return (T) Proxy.newProxyInstance(Rpcfx.class.getClassLoader(), new Class[]{serviceClass}, new RpcInvocationHandler(serviceClass, url));
    }

    public static class RpcInvocationHandler implements InvocationHandler {

        private final Class<?> serviceClass;

        private final String url;

        public <T> RpcInvocationHandler(Class<T> serviceClass, String url) {
            this.serviceClass = serviceClass;
            this.url = url;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
            RpcfxRequest req = new RpcfxRequest();
            req.setServiceClass(this.serviceClass.getName());
            req.setMethod(method.getName());
            req.setParams(params);
            RpcfxResponse response = (RpcfxResponse)HttpClient.httpClient(req,url);
            return JSON.parse(response.getResult().toString());
        }
    }
}
