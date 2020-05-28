package interview.threadPool;

import java.util.concurrent.*;

/**
 * @author zg
 * @create 2020-03-10 23:06
 */
public class myThreadPool {

    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());


        for(int i=0;i<6;i++){
            threadPool.execute(()->{
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"正在执行");
            });

        }
        threadPool.shutdown();

    }
}
