package util;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/16
 */
public class ThreadB extends Thread {

    private Object lock;

    public ThreadB() {
    }

    public ThreadB(Object lock) {
        this.lock = lock;
    }

    //    @Override
//    synchronized public void run() {
//        long start = System.currentTimeMillis();
//        System.out.println("ThreadB start: 0");
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("ThreadB end:"+ (System.currentTimeMillis()-start)+"ms");
//    }

    /**
     * notify 测试
     */
    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("开始发送通知"+System.identityHashCode(lock));
            lock.notify();
        }

        //会释放全部wait
//        synchronized (this) {
//            System.out.println("开始发送通知"+System.identityHashCode(this));
//            this.notify();
//        }
    }


    synchronized public void bMethod() {
        System.out.println("bMethod");
    }
}
