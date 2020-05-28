package leetcode.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zg
 * @create 2020-03-08 14:35
 *
 * 打印零与奇偶数[中等]
 *
 */
public class Test1116 {
    private int n;

    int flag = 0;
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    public Test1116(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero() throws InterruptedException {
        lock.lock();
        for(int i = 0;i<n;i++){

            if(flag!=0 && flag!=2) condition1.await();
            System.out.println(0);
            if(flag==2){
                flag = 3;
                condition3.signal();
                condition1.await();
            }else if(flag==0){
                flag = 1;
                condition2.signal();
                condition1.await();

            }


        }
        condition2.signal();
        condition3.signal();
        lock.unlock();
    }

    public void odd() throws InterruptedException {
        lock.lock();
        for(int i = 1;i<=n;i=i+2){

            if(flag!=1) condition2.await();
            System.out.println(i);
            flag=2;
            condition1.signal();
            condition2.await();

        }
        condition1.signal();
        lock.unlock();
    }

    public void even() throws InterruptedException {
        lock.lock();
        for(int i = 2;i<=n;i+=2){

            if(flag!=3) condition3.await();
            flag = 0;
            System.out.println(i);

            condition1.signal();
            condition3.await();

        }
        condition1.signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        Test1116 test1116 = new Test1116(1);
        class A implements Runnable{
            A(Test1116 test11161){}

            @Override
            public void run() {
                try {
                    test1116.zero();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        class B implements Runnable{
            B(Test1116 test11161){}

            @Override
            public void run() {
                try {
                    test1116.odd();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        class C implements Runnable{
            C(Test1116 test11161){}

            @Override
            public void run() {
                try {
                    test1116.even();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        new Thread(new A(test1116)).start();
        new Thread(new B(test1116)).start();
        new Thread(new C(test1116)).start();


    }

}
