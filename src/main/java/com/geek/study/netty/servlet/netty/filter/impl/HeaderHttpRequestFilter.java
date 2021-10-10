package com.geek.study.netty.servlet.netty.filter.impl;

import com.geek.study.netty.servlet.netty.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("request-filter", "header-req");
    }
}
