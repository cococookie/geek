package com.geek.study.thread;

import com.geek.study.thread.util.CountDownLatchTask;
import com.geek.study.thread.util.PCValue;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * 主线程等待线程结束拿到返回结果
 * @Author: siyan.liu
 * @Date: 2021/10/17
 */
public class GetResultThreadTest {

    private Integer value = 0;

    /**
     * 主线程使用join等待线程结束 打印结果
     */
    public void join() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                value += 10;
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("获取结果："+value);
    }

    /**
     * 类似生产消费者模式， 主线程为消费者
     */
    public void waitTest() {
        final Object lock = new Object();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    value += 10;
                    lock.notifyAll();
                }
            }
        });

        try {
            synchronized (lock) {
                if (value == 0) {
                    thread.start();
                    lock.wait();
                }
                System.out.println("获取结果："+value);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void countDownLatch() {
        PCValue pcValue = new PCValue();
        pcValue.setIntValue(0);
        CountDownLatch latch = new CountDownLatch(1);
        CompletableFuture<Void> future = CompletableFuture.runAsync(new CountDownLatchTask(latch,pcValue));
        try {
            latch.await();
            future.get();
            System.out.println("获取结果："+pcValue.getIntValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    /**
     * 使用submit的Future获取返回结果
     */
    public void executor() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 10;
            }
        });
        try {
            System.out.println("获取结果："+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    public void futureTask() {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "abc";
            }
        });
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            System.out.println("获取结果："+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * CompletableFuture
     */
    public void supply() {
        String reuslt = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "abc";
            }
        }).join();
        System.out.println("获取结果："+reuslt);
    }



    public static void main(String[] args) {
        GetResultThreadTest test = new GetResultThreadTest();
        test.futureTask();
    }
}
