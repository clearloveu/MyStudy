package designPattern.SingletonPattern;

/**
 * @author zg
 * @create 2020-02-29 18:01
 */
public class Singleton2 {
    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载         (在类加载过程中的加载链接初始化中的初始化过程)
     */
    private static class SingletonHolder{
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static Singleton2 instance = new Singleton2();
    }
    /**
     * 私有化构造方法
     */
    private Singleton2(){
    }
    public static  Singleton2 getInstance(){
        return SingletonHolder.instance;
    }
}



