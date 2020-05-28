package niuke;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * 百度笔试3.29
 *
 */
public class Baidu2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        String s = in.nextLine();
        s = s.trim();
        String[] temp  = s.split(" ");
        BigInteger[] a = new BigInteger[n];
        for (int i =0 ;i<temp.length;i++){
            a[i] = new BigInteger(temp[i]);
        }

//        String[] b = new String[n];
//        int[] a = new int[n];
//        for (int i =0;i<n;i++){
//            a[i] = in.nextInt();
//        }


//        int n = 3;
//        int[] a = {1,0,6};

        //贪心
        BigInteger res  = new BigInteger("0") ;
        BigInteger N = new BigInteger(String.valueOf(n));
        while (true){
            Arrays.sort(a);
            if (a[n-1].compareTo(N) <0) break;
            BigInteger jianNum = a[n-1].divide(N);
            res = res.add(jianNum);
            a[n-1] = a[n-1].subtract(jianNum.multiply(N));
            for (int i=0;i<n-1;i++) a[i] = a[i].add(jianNum);

        }

        System.out.println(res.toString());

    }
}
