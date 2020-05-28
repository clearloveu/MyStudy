package interview.JUC.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zg
 * @create 2020-03-05 17:51
 *
 * 三个线程一起卖1000张票，每卖出一百张，票价涨100元，原价500元，最后打印每个线程卖出去的票和总钱数
 *
 */
public class Ticket {
    AtomicInteger ticketNumSum = new AtomicInteger(1000);
//    int ticketNumSum = 1000;

    public static void main(String[] args) {
        Ticket abc = new Ticket();
        new Thread(abc.new A()).start();
        new Thread(abc.new B()).start();
        new Thread(abc.new C()).start();
    }


    class A implements Runnable{
        int ticketNum = 0;
        int ticketValue = 500;
        int valueRes = 0;
        int ticketRes = 0;

        @Override
        public void run() {
            while(true) {
                ticketNumSum.decrementAndGet();
                if (ticketNumSum.get() < 0) break;
                if (ticketNum == 100) {
                    ticketValue += 100;
                    ticketNum = 0;
                } else {
                    ticketNum++;
                }
                valueRes += ticketValue;
                ticketRes+=1;
            }
            System.out.println("ticketRes: "+ticketRes+"   "+"valueRes: "+valueRes);
        }
    }
    class B implements Runnable{
        int ticketNum = 0;
        int ticketValue = 500;
        int valueRes = 0;
        int ticketRes = 0;

        @Override
        public void run() {
            while(true) {
                ticketNumSum.decrementAndGet();
                if (ticketNumSum.get() < 0) break;
                if (ticketNum == 100) {
                    ticketValue += 100;
                    ticketNum = 0;
                } else {
                    ticketNum++;
                }
                valueRes += ticketValue;
            }
            System.out.println("ticketRes: "+ticketRes+"   "+"valueRes: "+valueRes);
        }
    }
    class C implements Runnable{
        int ticketNum = 0;
        int ticketValue = 500;
        int valueRes = 0;
        int ticketRes = 0;

        @Override
        public void run() {
            while(true) {
                ticketNumSum.decrementAndGet();
                if (ticketNumSum.get() < 0) break;
                if (ticketNum == 100) {
                    ticketValue += 100;
                    ticketNum = 0;
                } else {
                    ticketNum++;
                }
                valueRes += ticketValue;
            }
            System.out.println("ticketRes: "+ticketRes+"   "+"valueRes: "+valueRes);
        }
    }
}
