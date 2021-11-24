package com.geek;

/**
 * @Author: siyan.liu
 * @Date: 2021/11/7
 */
public class DynamicDataSourceSwitch {

    public static final ThreadLocal holder = new ThreadLocal();

    public DynamicDataSourceSwitch() {}

    public static void setDataSource(String name) {
        holder.set(name);
    }

    public static String getDataSource() {
        return (String)holder.get();
    }

}
