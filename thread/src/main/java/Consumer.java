import util.PCValue;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/17
 */
public class Consumer {
    private Object lock;

    public Consumer(Object lock) {
        this.lock = lock;
    }

    public void consum() {
        try {
            synchronized (lock) {
                if (PCValue.value == "") {
//                    System.out.println("consum 获取锁，但需要等待生产");
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName()+"-消费value:"+PCValue.value);
                PCValue.value = "";
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        final Object lock = new Object();
        Thread p1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Provider p = new Provider(lock);
                for (int i= 0;i<100;i++) {
                    p.product();
                }
            }
        });

        Thread p2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Provider p = new Provider(lock);
                for (int i= 0;i<100;i++) {
                    p.product();
                }
            }
        });

        Thread c1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Consumer c = new Consumer(lock);
                for (int i= 0;i<100;i++) {
                    c.consum();
                }
            }
        });
        Thread c2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Consumer c = new Consumer(lock);
                for (int i= 0;i<100;i++) {
                    c.consum();
                }
            }
        });

        p1.start();
//        p2.start();
        c1.start();
        c2.start();
    }
}
