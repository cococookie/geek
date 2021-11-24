package servlet.netty;


import servlet.netty.base.NettyHttpServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/10
 */
public class NettyServerApplication {

    public static void main(String[] args) {
        int port = 8888;
        List<String> serversList = new ArrayList<String>(Arrays.asList("http://localhost:8801","http://localhost:8802"));

        NettyHttpServer nettyHttpServer = new NettyHttpServer(port,serversList);
        nettyHttpServer.gatewayListen();
    }
}
