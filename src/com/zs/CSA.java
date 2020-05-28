package com.zs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author zg
 * @create 2020-02-25 18:20
 */
public class CSA {

    private int i = 0;



    private static Unsafe UNSAFE = null;
    private static long I_OffSET ;
    static {

        try {
//            UNSAFE = Unsafe.getUnsafe();  //直接操作内存的对象会报异常（CSA的类加载器是app）

            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);

            I_OffSET = UNSAFE.objectFieldOffset(CSA.class.getDeclaredField("i"));//CSA类中属性i的偏移量

        }catch (NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }

    }


    private  static void arraytest(){



        String[] strings = {"one","two","three","four"};
        //数组中存储的对象的对象头大小
        int ns = UNSAFE.arrayIndexScale(String[].class);
        //数组中第一个元素的起始位置
        int base = UNSAFE.arrayBaseOffset(String[].class);
        System.out.println(UNSAFE.getObject(strings,base+3*ns));
    }


    public static void main(String[] args) {
        arraytest();



        final  CSA csa = new CSA();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
//                    csa.i++;
                    // 第一个参数，操作的对象，第二个参数，属性在该对象的偏移量，第三个参数，期望的值，第四个参数，要赋值的值
                    // 如果内存的值和期望的值一样，则直接给内存的值进行赋值
                    boolean f = UNSAFE.compareAndSwapInt(csa,I_OffSET,csa.i,csa.i+1);//这样写代替了原来的 i++

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(f){
                        System.out.println(csa.i);//如果直接取，则在此处仍然会发生并发异常，所以还是使用Unsafe这个类
//                        System.out.println(UNSAFE.getIntVolatile(csa,I_OffSET));
                    }

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
//                    csa.i++;
                    boolean f = UNSAFE.compareAndSwapInt(csa,I_OffSET,csa.i,csa.i+1);

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(f){
                        System.out.println(csa.i);
//                        System.out.println(UNSAFE.getIntVolatile(csa,I_OffSET));
                    }
                }
            }
        }).start();

    }



}
