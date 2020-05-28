package leetcode.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author zg
 * @create 2020-03-08 16:05
 *
 * H2O 生成[中等]
 * 现在有两种线程，氢 oxygen 和氧 hydrogen，你的目标是组织这两种线程来产生水分子。
 *
 */
public class Test1117 {
    //题目说明是2种线程，而不是2个线程，所以仅仅一个CyclicBarrier是不够的，因为可能产生氧气O的线程先来2个，导致先打印2次O，
    //因为CyclicBarrier只会限制有3个线程被阻塞，而不会管哪3个线程，所以需要2个信号量限制H和O线程的同步
    class H2O {
        Semaphore s1 = new Semaphore(2);
        Semaphore s2 = new Semaphore(1);
        CyclicBarrier cb = new CyclicBarrier(3);
        public H2O() {

        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            s1.acquire();
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();

            try {
                cb.await();
            }
            catch (BrokenBarrierException e) {
            }
            s1.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            s2.acquire();
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            try {
                cb.await();
            }
            catch (BrokenBarrierException e) {
            }
            s2.release();
        }
    }
}
