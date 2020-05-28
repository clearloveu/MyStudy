package interview.JUC.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author zg
 * @create 2020-03-07 12:43
 */
public class Test2 {

    int i = 0;
    Object lock = new Object();
    CountDownLatch countDownLatch = new CountDownLatch(10);

    class Th implements Runnable{

        @Override
        public  void run() {
            for(int j=0;j<100;j++){
                synchronized(lock){
                    i++;
                }
            }
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Test2 t = new Test2();
        Thread[] threads = new Thread[10];
        for(int j=0;j<10;j++){
            Thread thread = new Thread(t.new Th());
            threads[j] = thread;
        }
        for(int j=0;j<10;j++){
            threads[j].start();
        }
        t.countDownLatch.await();
        System.out.println(t.i);
    }
}
