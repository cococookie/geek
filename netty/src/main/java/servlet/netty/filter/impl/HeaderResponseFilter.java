package servlet.netty.filter.impl;

import io.netty.handler.codec.http.FullHttpResponse;
import servlet.netty.filter.HttpResponseFilter;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/10
 */
public class HeaderResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("response-filter", "header-resp");
    }
}
