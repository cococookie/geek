package com.geek.study.jvm;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

/**
 * 启动加载器、扩展加载器、应用加载器路径打印
 * @Author: siyan.liu
 * @Date: 2021/9/19
 */
public class ClassloaderText {
    public static void main(String[] args) {

        //启动加载器
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动加载器:");
        for (URL url : urls) {
            System.out.println(url.toExternalForm());
        }
        //扩展加载器 加载lib/ext文件夹下的路径 应用加载器的父类
        ClassLoader extClassLoader = ClassloaderText.class.getClassLoader().getParent();
        printLoaderPath("扩展加载器：",extClassLoader);

        //应用加载器 会打印父类(扩展加载器、启动加载器)的路径，还有当前文件所在的路径以及依赖的jar包
        ClassLoader appClassLoader = ClassloaderText.class.getClassLoader();
        printLoaderPath("应用加载器：",appClassLoader);

    }

    private static void printLoaderPath(String loaderName,ClassLoader classLoader) {
        try {
            System.out.println(loaderName);
            if (classLoader instanceof URLClassLoader) {
                Field ucpField = URLClassLoader.class.getDeclaredField("ucp");
                ucpField.setAccessible(true);
                Object ucpObj = ucpField.get(classLoader);
                System.out.println(ucpObj);
                Field pathField = ucpObj.getClass().getDeclaredField("path");
                pathField.setAccessible(true);
                List pathList = (List)pathField.get(ucpObj);
                for (Object path : pathList) {
                    System.out.println("path: "+ path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
