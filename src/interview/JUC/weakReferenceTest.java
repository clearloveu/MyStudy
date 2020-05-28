package interview.JUC;

import java.lang.ref.WeakReference;

/**
 * @author zg
 * @create 2020-03-03 18:16
 *
 * java引用分为强引用、弱引用、软引用、虚引用
 * 测试弱引用
 *
 */
public class weakReferenceTest {



    public static void main(String[] args) {
        WeakReference<JULTest> weakReference = new WeakReference<>(new JULTest());
        System.out.println(weakReference.get());
        System.gc();
        //弱引用是一个类，只要gc，弱引用就会被回收
        System.out.println(weakReference.get());


    }


}
