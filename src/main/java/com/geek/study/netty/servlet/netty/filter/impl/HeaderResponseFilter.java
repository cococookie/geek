package com.geek.study.netty.servlet.netty.filter.impl;

import com.geek.study.netty.servlet.netty.filter.HttpResponseFilter;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/10
 */
public class HeaderResponseFilter implements HttpResponseFilter{
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("response-filter", "header-resp");
    }
}
