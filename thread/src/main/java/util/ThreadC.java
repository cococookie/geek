package util;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/16
 */
public class ThreadC extends Thread{

    private ThreadB threadB;

    public ThreadC(ThreadB threadB) {
        this.threadB = threadB;
    }

    @Override
    public void run() {
        System.out.println("ThreadC");
        threadB.bMethod();
    }
}
