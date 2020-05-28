package interview.JUC.Test;

/**
 * @author zg
 * @create 2020-03-05 17:51
 *
 * 三个线程一起卖1000张票，每卖出一百张，票价涨100元，原价500元，最后打印每个线程卖出去的票和总钱数
 *
 */
public class Ticket2 {
    int ticketNumSum = 1000;
    final Object lock = new Object();
//    int ticketNumSum = 1000;

    public static void main(String[] args) {
        Ticket2 abc = new Ticket2();
        new Thread(abc.new A()).start();
        new Thread(abc.new B()).start();
        new Thread(abc.new C()).start();
    }


    class A implements Runnable{
        int ticketNum = 0;
        int ticketValue = 500;
        int res = 0;

        @Override
        public void run() {
            while(true) {
                synchronized (lock) {
                    ticketNumSum--;
                    if (ticketNumSum <= 0) break;
                    if (ticketNum == 100) {
                        ticketValue += 100;
                        ticketNum = 0;
                    } else {
                        ticketNum++;
                    }
                    res += ticketValue;
                }
            }
            System.out.println(res);
        }
    }
    class B implements Runnable{
        int ticketNum = 0;
        int ticketValue = 500;
        int res = 0;

        @Override
        public void run() {
            while(true) {
                synchronized (lock) {
                    ticketNumSum--;
                    if (ticketNumSum <= 0) break;
                    if (ticketNum == 100) {
                        ticketValue += 100;
                        ticketNum = 0;
                    } else {
                        ticketNum++;
                    }
                    res += ticketValue;
                }
            }
            System.out.println(res);
        }
    }
    class C implements Runnable{
        int ticketNum = 0;
        int ticketValue = 500;
        int res = 0;

        @Override
        public void run() {
            while(true) {
                synchronized (lock) {
                    ticketNumSum--;
                    if (ticketNumSum <= 0) break;
                    if (ticketNum == 100) {
                        ticketValue += 100;
                        ticketNum = 0;
                    } else {
                        ticketNum++;
                    }
                    res += ticketValue;
                }
            }
            System.out.println(res);
        }
    }
}
