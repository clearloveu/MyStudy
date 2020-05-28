package interview.JUC.CountDownLatch;

/**
 * @author zg
 * @create 2020-03-07 12:43
 */
public class Test1 {

    int i = 0;
    Object lock = new Object();

    class Th implements Runnable{

        @Override
        public  void run() {
            for(int j=0;j<100;j++){
                synchronized(lock){
                    i++;
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Test1 t = new Test1();
        Thread[] threads = new Thread[10];
        for(int j=0;j<10;j++){
            Thread thread = new Thread(t.new Th());
            threads[j] = thread;
        }
        for(int j=0;j<10;j++){
            threads[j].start();
        }
        for(int j=0;j<10;j++){
            threads[j].join();
        }
        System.out.println(t.i);
    }
}
