package interview.JDKAOP;


import java.lang.reflect.Proxy;

/**
 * @author zg
 * @create 2020-03-01 10:26
 */
public class MyProxy {

    // 参考：https://blog.csdn.net/jiankunking/article/details/52143504


    public static void main(String[] args) {
        AOP target = new AOPImpl();
        Class[] classes = target.getClass().getInterfaces();
        AOP proxy = (AOP)Proxy.newProxyInstance(target.getClass().getClassLoader(),classes,new MyHandler(target));
        proxy.helloWorld();


    }




}
