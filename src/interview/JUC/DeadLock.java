package interview.JUC;

/**
 * @author zg
 * @create 2020-03-06 11:42
 */
public class DeadLock {


    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();


        new Thread(()->{
            synchronized (a){
                System.out.println("thread1 get lock a");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b){
                    System.out.println("thread1 get lock b");
                }
            }
        }).start();
        new Thread(()->{
            synchronized (b){
                System.out.println("thread2 get lock b");
                synchronized (a){
                    System.out.println("thread2 get lock a");
                }
            }
        }).start();


    }


}
