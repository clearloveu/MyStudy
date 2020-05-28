package leetcode.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zg
 * @create 2020-03-08 16:42
 *
 * 哲学家进餐[中等]
 *
 */
public class Test1226 {
    //重点：
    // 1，自己构造临界区资源，即构造5个筷子
    // 2，保证每个筷子只能被一个线程所拥有
    // 避免死锁的方法：
    // 1，奇数哲学家拿左边筷子，偶数哲学家拿右边筷子（本解法）
    // 2，限制最多只有4个哲学家拿筷子，用信号量
    // 3，当只有能同时拥有左右筷子时，才拿起筷子
    class DiningPhilosophers {

        private ReentrantLock[] lockList = {new ReentrantLock(),
                new ReentrantLock(),
                new ReentrantLock(),
                new ReentrantLock(),
                new ReentrantLock()};

        public DiningPhilosophers() {

        }

        // call the run() method of any runnable to execute its code
        public void wantsToEat(int philosopher,
                               Runnable pickLeftFork,
                               Runnable pickRightFork,
                               Runnable eat,
                               Runnable putLeftFork,
                               Runnable putRightFork) throws InterruptedException {

            int leftFork = (philosopher + 1) % 5;	//左边的叉子 的编号
            int rightFork = philosopher;	//右边的叉子 的编号

            if((philosopher&1)==1){
                lockList[leftFork].lock();
                lockList[rightFork].lock();
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putRightFork.run();
                putLeftFork.run();
                lockList[rightFork].unlock();
                lockList[leftFork].unlock();
            }
            else {
                lockList[rightFork].lock();
                lockList[leftFork].lock();
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putRightFork.run();
                putLeftFork.run();
                lockList[leftFork].unlock();
                lockList[rightFork].unlock();
            }

        }
    }
}
