package interview.JDKAOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zg
 * @create 2020-03-01 10:32
 */
public class MyHandler implements InvocationHandler {


    private Object aop ;

    //代理目标由构造函数传进来
    MyHandler(Object aop){
        this.aop =  aop;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        System.out.println("method:"+method);
        Object res = method.invoke(aop,args);  //proxy是代理类实例，而不是被代理的目标，这里传入一个被代理的目标（target由构造函数传进来）
        System.out.println("after");
        return res;
    }
}
