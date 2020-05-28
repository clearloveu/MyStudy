package interview.JUC.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zg
 * @create 2020-03-05 17:07
 *
 * 使用AtomicInteger类让3个线程依次打印A，B，C
 *
 *
 */
public class ABC2 {

    AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        ABC2 abc = new ABC2();
        new Thread(abc.new a()).start();
        new Thread(abc.new b()).start();
        new Thread(abc.new c()).start();
    }
    class a implements Runnable{
        @Override
        public void run() {
            while (true) {
                if (atomicInteger.get() == 1) {
                    System.out.println("A");
                    atomicInteger.incrementAndGet();
                }
            }
        }
    }
    class b implements Runnable{
        @Override
        public void run() {
            while (true) {
                if (atomicInteger.get() == 2) {
                    System.out.println("B");
                    atomicInteger.incrementAndGet();
                }
            }
        }
    }
    class c implements Runnable{
        @Override
        public void run() {
            while (true) {
                if (atomicInteger.get() == 3) {
                    System.out.println("C");
                    atomicInteger.set(1);
                }
            }
        }
    }

}
