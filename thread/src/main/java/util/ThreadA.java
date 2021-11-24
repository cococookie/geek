package util;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/16
 */
public class ThreadA extends Thread{

    private ThreadB threadB;

    public ThreadA(ThreadB threadB) {
        this.threadB = threadB;
    }

    /**
     * joinTest():
     *
     * ThreadA
     * ThreadB start: 0
     * ThreadC
     * (1s)
     * 释放B锁
     * bMethod
     * (4s)
     * ThreadB end:
     * ThreadA结束
     *
     *
     * sleepTest():
     *
     * ThreadA
     * ThreadB start: 0
     * ThreadC
     * (5s)
     * ThreadB end:
     * (1s)
     * ThreadA结束,释放B锁
     * bMethod
     *
     */
    @Override
    public void run() {
        synchronized (threadB) {
            System.out.println("ThreadA");
            threadB.start();
            sleepTest();
//            joinTest();

        }
    }


    private void sleepTest() {
        try {
            Thread.sleep(6000);
            System.out.println("ThreadA结束,释放B锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void joinTest() {
        try {
            Thread.sleep(1000);
            System.out.println("释放B锁");
            threadB.join();
            System.out.println("ThreadA结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
