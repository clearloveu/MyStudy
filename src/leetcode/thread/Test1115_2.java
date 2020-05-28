package leetcode.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zg
 * @create 2020-03-07 23:20
 *
 * 交替打印FooBar[中等]
 *
 */
public class Test1115_2 {
    class FooBar {
        private int n;

        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        boolean flag = true;
        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            lock.lock();
            for (int i = 0; i < n; i++) {

                if(flag==false) condition1.await();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                flag = false;
                condition2.signal();
                condition1.await();


            }
            condition2.signal();
            lock.unlock();
        }

        public void bar(Runnable printBar) throws InterruptedException {
            lock.lock();
            for (int i = 0; i < n; i++) {
                if(flag==true) condition2.await();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                flag = true;
                condition1.signal();
                condition2.await();

            }
            lock.unlock();
        }
    }
}
