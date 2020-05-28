package interview.JUC.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zg
 * @create 2020-03-04 23:41
 *
 * 使用ReentrantLock和Condition类让3个线程依次打印A，B，C
 *
 */
public class ABC {
    int flag = 1;
    final  Lock lock = new ReentrantLock();
    final  Condition condition1 = lock.newCondition();
    final  Condition condition2 = lock.newCondition();
    final  Condition condition3 = lock.newCondition();

    public static void main(String[] args) {
        ABC abc = new ABC();
        new Thread(abc.new a()).start();
        new Thread(abc.new b()).start();
        new Thread(abc.new c()).start();
    }
    class a implements Runnable{
        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    if(flag!=1) condition1.await();
                    System.out.println("A");
                    flag +=1;
                    condition2.signal();
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }
    class b implements Runnable{
        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    if(flag!=2) condition2.await();
                    System.out.println("B");
                    flag +=1;
                    condition3.signal();
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
    class c implements Runnable{
        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    if(flag!=3) condition3.await();
                    System.out.println("C");
                    flag =1;
                    condition1.signal();
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

