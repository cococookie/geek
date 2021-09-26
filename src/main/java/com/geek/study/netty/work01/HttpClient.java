package com.geek.study.netty.work01;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Author: siyan.liu
 * @Date: 2021/9/26
 */
public class HttpClient {

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        try {
            // 创建Get请求
            String url = "http://localhost:8801/";
            HttpGet httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(2000) //服务器响应超时时间
                    .setConnectTimeout(2000) //连接服务器超时时间
                    .build();
            httpGet.setConfig(requestConfig);
            //发送请求
            response = httpClient.execute(httpGet);
            //获取响应对象
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容:" + EntityUtils.toString(responseEntity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
