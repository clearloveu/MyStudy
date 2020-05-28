package leetcode.thread;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author zg
 * @create 2020-03-08 14:35
 */
public class Test1116_2 {
    //信号量法
    class ZeroEvenOdd {
        private int n;

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        Semaphore z = new Semaphore(1);
        Semaphore e = new Semaphore(0);
        Semaphore o = new Semaphore(0);

        public void zero(IntConsumer printNumber) throws InterruptedException {
            for(int i=0; i<n;i++) {
                z.acquire();
                printNumber.accept(0);
                if((i&1)==0) {
                    o.release();
                }else {
                    e.release();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for(int i=2; i<=n; i+=2) {
                e.acquire();
                printNumber.accept(i);
                z.release();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for(int i=1; i<=n; i+=2) {
                o.acquire();
                printNumber.accept(i);
                z.release();
            }
        }
    }
}
