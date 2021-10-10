package com.geek.study.netty.servlet.easy;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: siyan.liu
 * @Date: 2021/9/26
 */
public class HttpServer01 {

    public static void main(String[] args) {
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                openSocket(8801);
            }
        };
        runnable1.run();

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                openSocket(8802);
            }
        };
        runnable2.run();

    }

    public static void openSocket(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            dealSocket(socket,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dealSocket(Socket socket,int port) {
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-Type:text/html;charset=UTF-8");
            String body = "HttpServer:"+port;
            pw.println("Content-Length:"+body.getBytes().length);
            pw.println();
            pw.write(body);
            pw.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
