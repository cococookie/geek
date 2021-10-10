package com.geek.study.netty.servlet.netty.inbound;

import com.geek.study.netty.servlet.netty.filter.HttpRequestFilter;
import com.geek.study.netty.servlet.netty.filter.impl.HeaderHttpRequestFilter;
import com.geek.study.netty.servlet.netty.outbound.HttpOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

import java.util.List;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/10
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private List<String> proxyServer;

    private HttpRequestFilter requestFilter = new HeaderHttpRequestFilter();

    private HttpOutboundHandler handler;

    public HttpInboundHandler(List<String> proxyServer) {
        this.proxyServer = proxyServer;
        this.handler = new HttpOutboundHandler(this.proxyServer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;

            handler.handle(fullRequest, ctx, requestFilter);

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
