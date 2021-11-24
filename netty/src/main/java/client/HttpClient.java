package client;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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
        httpClient();
    }

    public static String httpClient() {
        String url = "http://localhost:8801/";
        return httpClient(url);
    }

    public static String httpClient(String url) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        String content = null;
        try {
            // 创建Get请求

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
                content = EntityUtils.toString(responseEntity);
                System.out.println("响应内容:" + content);
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
        return content;
    }


    public static Object httpClient(Object req, String url) {
        Object rpcfxResponse = new Object();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        String content = null;
        try {
            // 创建Get请求

            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(2000) //服务器响应超时时间
                    .setConnectTimeout(2000) //连接服务器超时时间
                    .build();
            httpPost.setConfig(requestConfig);
            StringEntity se = new StringEntity(JSONObject.toJSONString(req));
            httpPost.setEntity(se);
            httpPost.setHeader("Content-Type","application/json; charset=utf-8");
            //发送请求
            response = httpClient.execute(httpPost);
            //获取响应对象
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态:" + response.getStatusLine());

            if (responseEntity != null) {
                content = EntityUtils.toString(responseEntity);
                System.out.println("响应内容:" + content);
//                rpcfxResponse = JSONObject.parseObject(content,RpcfxResponse.class);
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
        return rpcfxResponse;
    }

}
