package com.geek.rpc.req;

/**
 * @Author: siyan.liu
 * @Date: 2021/11/17
 */
public class RpcfxRequest {

    private String serviceClass;

    private String method;

    private Object[] params;

    public String getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
