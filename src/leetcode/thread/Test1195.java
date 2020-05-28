package leetcode.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.function.IntConsumer;

/**
 * @author zg
 * @create 2020-03-09 11:33
 *
 * 交替打印字符串[中等]
 *
 */
public class Test1195 {
    class FizzBuzz {
        private int n;

        private CyclicBarrier barrier = new CyclicBarrier(4);
        public FizzBuzz(int n) {
            this.n = n;
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            for(int i =1;i<=n;i++){
                if((i%3==0)&&(i%5!=0)) printFizz.run();
                try {
                    barrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }

        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            for(int i =1;i<=n;i++){
                if((i%5==0)&&(i%3!=0)) printBuzz.run();
                try {
                    barrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }

        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            for(int i =1;i<=n;i++){
                if((i%3==0)&&(i%5==0)) printFizzBuzz.run();
                try {
                    barrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }

        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            for(int i =1;i<=n;i++){
                if((i%3!=0)&&(i%5!=0)) printNumber.accept(i);
                try {
                    barrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
