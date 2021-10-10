package com.geek.study.netty.servlet.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * @Author: siyan.liu
 * @Date: 2021/10/10
 */
public class HttpInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    public void initChannel(SocketChannel socketChannel){
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast(new HttpServerCodec());//编码器
        p.addLast(new HttpObjectAggregator(1024 * 1024));//聚合器
        p.addLast(new HttpHandler());
    }
}
