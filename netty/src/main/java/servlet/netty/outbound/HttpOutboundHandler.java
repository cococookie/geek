package servlet.netty.outbound;

import client.HttpClient;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import servlet.netty.filter.HttpRequestFilter;
import servlet.netty.filter.HttpResponseFilter;
import servlet.netty.filter.impl.HeaderResponseFilter;
import servlet.netty.router.HttpEndpointRouter;
import servlet.netty.router.RandomHttpEndpointRouter;


import java.util.List;

import static io.netty.handler.codec.http.HttpHeaderNames.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/10
 */
public class HttpOutboundHandler {

    private List<String> urls;

    HttpEndpointRouter router = new RandomHttpEndpointRouter();

    HttpResponseFilter responseFilter = new HeaderResponseFilter();

    public HttpOutboundHandler(List<String> urls) {
        this.urls = urls;
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter) {
        String backendUrl = router.route(this.urls);
        final String url = backendUrl + fullRequest.uri();
        filter.filter(fullRequest, ctx);
        String body = HttpClient.httpClient(url) + "";
        //构造返回实体
        FullHttpResponse response = null;
        try {
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body.getBytes("UTF-8")));
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
