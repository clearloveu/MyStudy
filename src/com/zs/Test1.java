package com.zs;

import java.math.BigDecimal;

/**
 * @author zg
 * @create 2019-12-05 20:46
 */
public class Test1 {



    public static  void main(String[] args)  {

        //测试java整数除法
//        System.out.println(5/2);
//        System.out.println(7/2);


        //测试String子串问题
//        String s = "a";
//        String d = s.substring(1);
//        System.out.println(d);
//        int[] a={};
//        System.out.println(a[0]);

//        String b = "123456";
//        char[] cs = b.toCharArray();
//
//        for (int i = 0 ; i<cs.length;i++){
//            System.out.println(cs[i]);
//        }
//
//        char c = '0';
//        System.out.println((int)c);


//        //矩形类
//        class Node{
//            public Node(int chang,int kuan)
//            {
//                this.chang=chang;
//                this.kuan=kuan;
//            }
//            int chang;
//            int kuan;
//        }
//
//        Comparator<Node> cNode=new Comparator<Node>() {
//            public int compare(Node o1, Node o2) {
//                if(o1.chang!=o2.chang)
//                    return o1.chang-o2.chang;
//                else
//                    return o2.kuan-o1.kuan;
//            }
//
//        };
//
//        Queue<Node> q=new PriorityQueue<>(cNode);
//        Node n1=new Node(1, 2);
//        Node n2=new Node(2, 5);
//        Node n3=new Node(2, 3);
//        Node n4=new Node(1, 2);
//        q.add(n1);
//        q.add(n2);
//        q.add(n3);
//        Node n;
//        while(!q.isEmpty())
//        {
//            n=q.poll();
//            System.out.println("长: "+n.chang+" 宽：" +n.kuan);
//        }


//        System.out.println("1"+(2*2+2)+"3");
//        String s ="123";
//        System.out.println(s.substring(1,2));
//
//        System.out.println(198&1);

//        System.out.println(-1>>4);
//        System.out.println(-1<<3);
//        System.out.println((int)Math.pow(10,2));
//        throw new RuntimeException("12");

//        LockSupport.park();
//        ConcurrentHashMap c = new ConcurrentHashMap();
//        c.put();
//        Lock lock = new ReentrantLock();
//        Condition condition = lock.newCondition();
//        lock.lock();
//        lock.unlock();
//        condition.await();
//        condition.signal();

//        TransferQueue<String> queue = new LinkedTransferQueue<>();
//        queue.transfer("my data");
//        queue.take();
//        HashMap<Test2,Integer> map=new HashMap();
//        map.put(new Test2(),2);
//        Class z = new Test1().getClass();

//        fori


//        class StudentComparator implements Comparator<Integer> {
//
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1-o2;
//            }
//        }
//
//        int[] nums = {2, 6, 4, 8, 10, 9, 15};
//        Arrays.sort(nums,new StudentComparator());

        System.out.println(Integer.valueOf("1"));
        BigDecimal bigDecimal = new BigDecimal("12");
        BigDecimal res = bigDecimal.add(new BigDecimal("-12"));
        System.out.println(res);
    }







}

