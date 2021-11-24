/**
 * 通过标识主动中断线程
 * @Author: siyan.liu
 * @Date: 2021/10/17
 */
public class InterruptedThreadTest {

    private boolean flag = false;

    public void add()  throws InterruptedException{
        int count = 0;
        for (int i =0;i<100;i++) {
            for (int j = 0;j<10000;j++) {
                count++;
            }
            System.out.println(count);
            Thread.sleep(100);
            if (flag) {
                System.out.println("状态改变");
                throw new InterruptedException();
            }
        }
    }

    public static void main(String[] args) {
        final InterruptedThreadTest t = new InterruptedThreadTest();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.flag = true;
    }
}
