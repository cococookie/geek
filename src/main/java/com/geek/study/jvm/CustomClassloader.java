package com.geek.study.jvm;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 自定义加载器
 * @Author: siyan.liu
 * @Date: 2021/9/19
 */
public class CustomClassloader extends ClassLoader {
    public static void main(String[] args) {
        try {
            Object helloObj = new CustomClassloader().findClass("Hello").newInstance();
            Method method = helloObj.getClass().getMethod("hello");
            method.invoke(helloObj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        byte[] newHelloByte = new byte[]{};
        try {
            byte[] xhelloByte = Files.readAllBytes(Paths.get("./src/main/java/com/geek/study/jvm/Hello.xlass"));
            newHelloByte = new byte[xhelloByte.length];
            for (int i = 0; i < xhelloByte.length; i++) {
                newHelloByte[i] = (byte)(255 - xhelloByte[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defineClass(name,newHelloByte,0,newHelloByte.length);
    }

}
