/**
 * 创建线程两种方式
 * @Author: siyan.liu
 * @Date: 2021/10/16
 */
public class CreateThread extends Thread {

    /**
     * 使用Runnable 匿名内部类实现run方法
     */
    public void testThread1() {
        Runnable task = new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                CreateThread.this.run();
            }
        };
        Thread thread = new Thread(task);
        //守护线程
//        thread.setDaemon(true);
        thread.start();
    }

    /**
     * 使用类继承Thread类， 重写run方法
     */
    public void testThread2() {
        CreateThread thread = new CreateThread();
        thread.start();
    }

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        for (int i = 0; i<10; i++) {
            System.out.println("线程"+t.getName()+ ":"+ i );
        }
    }

    public static void main(String[] args) {
        CreateThread createThread = new CreateThread();
        createThread.testThread1();
        createThread.testThread2();
    }
}
