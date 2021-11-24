import util.PCValue;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/17
 */
public class Provider {

    private Object lock;

    public Provider(Object lock) {
        this.lock = lock;
    }

    public  void product () {
        try {
            synchronized (lock) {
                if (PCValue.value != "") {
//                    System.out.println("product 获取锁，但需要等待消费");
                    lock.wait();
                }
                PCValue.value = System.currentTimeMillis()+"_"+System.nanoTime();
                System.out.println(Thread.currentThread().getName()+"-生产value"+ PCValue.value);
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
