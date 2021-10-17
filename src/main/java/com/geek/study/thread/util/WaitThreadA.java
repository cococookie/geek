package com.geek.study.thread.util;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/16
 */
public class WaitThreadA extends Thread{

    private Object lock;

    public WaitThreadA(Object lock) {
        this.lock = lock;
    }

    /**
     * wait()/notify方法测试
     */
    @Override
    public void run() {
        synchronized (lock) {
            long start = System.currentTimeMillis();
            System.out.println("WaitThreadA "+ Thread.currentThread().getName()+";"+System.identityHashCode(lock)+" start: 0");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("WaitThreadA "+ Thread.currentThread().getName()+"end:"+ (System.currentTimeMillis()-start)+"ms");
        }
    }
}
