package servlet.netty.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/10
 */
public interface HttpResponseFilter {
    void filter(FullHttpResponse response);
}
