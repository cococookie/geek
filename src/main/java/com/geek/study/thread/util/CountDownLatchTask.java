package com.geek.study.thread.util;

import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/17
 */
public class CountDownLatchTask implements Runnable {

    private CountDownLatch latch;

    private PCValue pcValue;

    public CountDownLatchTask(CountDownLatch latch, PCValue pcValue) {
        this.latch = latch;
        this.pcValue = pcValue;
    }

    @Override
    public void run() {
        pcValue.setIntValue(10);
        latch.countDown();
    }
}
