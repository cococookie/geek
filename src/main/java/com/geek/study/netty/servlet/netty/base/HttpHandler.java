package com.geek.study.netty.servlet.netty.base;

import com.geek.study.netty.client.HttpClient;
import com.geek.study.netty.servlet.netty.filter.HttpRequestFilter;
import com.geek.study.netty.servlet.netty.filter.HttpResponseFilter;
import com.geek.study.netty.servlet.netty.filter.impl.HeaderHttpRequestFilter;
import com.geek.study.netty.servlet.netty.filter.impl.HeaderResponseFilter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;

import static io.netty.handler.codec.http.HttpHeaderNames.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/10
 */
public class HttpHandler extends ChannelInboundHandlerAdapter {

    private HttpRequestFilter requestFilter = new HeaderHttpRequestFilter();

    private HttpResponseFilter responseFilter = new HeaderResponseFilter();

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            String uri = fullRequest.uri();
            if (uri.contains("/test")) {
                handlerTest(fullRequest, ctx, "test request");
            } else {
                handlerTest(fullRequest, ctx, "other request");
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx, String body) {
        FullHttpResponse response = null;
        try {
            //请求前加过滤
            requestFilter.filter(fullRequest, ctx);
            //整合httpClient
            String content = HttpClient.httpClient();
            String value = body + ":" + content;
            //构造返回实体
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
            //返回前加过滤
            responseFilter.filter(response);
        } catch (Exception e) {
            System.out.println("处理出错:"+e.getMessage());
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
                ctx.flush();
            }
        }
    }

}
