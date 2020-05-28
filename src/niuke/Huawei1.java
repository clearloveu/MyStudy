package niuke;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 */
public class Huawei1 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int n = in.nextInt();
            int l = in.nextInt();
            if (n==0&&l==0) continue;

            BigInteger N  = new BigInteger(String.valueOf(n));
            BigInteger temp = new BigInteger(String.valueOf(1));
            BigInteger res = new BigInteger(String.valueOf(0));
            BigInteger compare = new BigInteger(String.valueOf(1000000007));
//            while(l>0){
//                l--;
//                temp = temp.multiply(N);
//                res = res.add(temp);
//
//
//            }
            if (n==1) System.out.println(l);
            else {

                BigInteger one = new BigInteger(String.valueOf(1));
                res = N.pow(l+1).subtract(one);

                res = res.divide((N.subtract(one)));
                res = res.subtract(one);



                if (res.compareTo(compare)>0){
                    res = res.mod(compare);

                }
                System.out.println(res.toString());
            }


        }

    }

}
