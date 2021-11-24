package com.geek.rpc.provider;

import com.geek.rpc.req.RpcfxRequest;
import com.geek.rpc.resp.RpcfxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author: siyan.liu
 * @Date: 2021/11/18
 */
@Service
public class RpcfxInvoker {

    @Autowired
    private LoadService loadService;

    public RpcfxResponse invoke(RpcfxRequest req) {
        RpcfxResponse result = new RpcfxResponse();

        Object service = loadService.load(req.getServiceClass());
        Method[] methods = service.getClass().getMethods();
        Method method = Arrays.asList(methods).stream().filter(m -> m.getName().equals(req.getMethod())).findFirst().get();
        Object response = null;
        try {
            response = method.invoke(service,req.getParams());
            result.setStatus(true);
            result.setResult(response);
        } catch (Exception e) {
            result.setStatus(false);
            result.setException(e);
        }
        return result;
    }
}
