package leetcode.thread;

/**
 * @author zg
 * @create 2020-03-07 23:20
 *
 * 交替打印FooBar[中等]
 *
 */
public class Test1115_1 {
    //超出时间限制
    class FooBar {
        private int n;
        volatile boolean flag = true;
        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                while(true){
                    if(flag==true){
                        // printFoo.run() outputs "foo". Do not change or remove this line.
                        printFoo.run();
                        flag = false;
                        break;
                    }
                }

            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                while(true){
                    if(flag==false){
                        // printBar.run() outputs "bar". Do not change or remove this line.
                        printBar.run();
                        flag = true;
                        break;
                    }
                }

            }
        }
    }
}
