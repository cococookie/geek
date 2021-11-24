import util.ThreadA;
import util.ThreadB;
import util.ThreadC;
import util.WaitThreadA;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/16
 */
public class BlockThreadTest{

    /**
     * sleep（long millis）: 当前线程的方法，放弃获取的时间片cpu，但不释放锁,
     * 进入timed_waiting状态,时间到了之后，自动苏醒进入就绪状态，重新获取时间片
     * 作用：给其他线程执行的机会
     */

    /**
     * yield（）: 当前线程方法，放弃获取的时间片，但不释放锁，与sleep类似，
     * 但是没有时间参数，只是放弃cpu让os重新选择，也有可能被系统再次选中。
     * 作用：让出cpu
     */

    public static void yieldTest() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                int count = 0;
                for (int i = 0; i<5000000; i++) {
                    if (i % 100 == 0) {
                        Thread.yield();
                    }
                    count+=i;
                }
                System.out.println("耗时："+ (System.currentTimeMillis()-start)+"ms");
            }
        });
        thread.start();

    }

    /**
     * t.join（long millis）/t.join() ：当前线程等待线程t运行结束后，再继续执行,
     * 等待过程中当前线程进入waiting/timed_waiting状态,当前线程不会释放锁，
     * 但会释放t对象的同步锁。join执行完或时间到了之后，进入就绪状态。
     */
    public static void joinTest() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                for (int i = 0; i<5000000; i++) {
                    if (i % 1000000 == 0) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(i);
                    }
                    count++;
                }
                System.out.println(count);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程执行完毕后，打印此行");
    }

    /**
     * 注释详见ThreadA
     */
    public static void joinLockTest() {
        ThreadB b = new ThreadB();
        ThreadA a = new ThreadA(b);
        a.start();

        ThreadC c = new ThreadC(b);
        c.start();
    }

    /**
     * wait()/wait（long millis）：Object对象的方法，在线程调用wait之前，线程必须获取该对象的对象级别锁，
     * 也就是说，必须要在同步方法或者同步块中调用wait
     * 如果不获取该对象的锁，调用wait会报IllegalMonitorStateException，是运行时异常，不需要try catch
     * 执行wait会释放对象锁，时间到，或者依靠
     * notify()/notifyAll()方法唤醒
     */
    public static void waitTest() {

        ThreadB lock = new ThreadB();
        WaitThreadA a1 = new WaitThreadA(lock);
        a1.start();

        WaitThreadA a2 = new WaitThreadA(lock);
        a2.start();

        //只释放一个wait
        ThreadB b = new ThreadB(lock);
        b.start();

        //会释放所有wait
//        lock.start();
    }


    /**
     * obj.notify()/notifyAll(): 唤醒obj对象的线程,与wait方法一样，必须在同步块/方法中调用notify，当有多个线程
     * 等待该对象锁时，会随机挑选一个通知。发送通知之前，必须要执行完代码释放锁后才会发送通知。
     */
    public  void notifyTest() {
        //waitTest（）方法
    }

    public static void main(String[] args) {
//        yieldTest();
//        joinTest();
//        joinLockTest();
        waitTest();
    }

}
