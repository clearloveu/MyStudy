package leetcode;

import java.math.BigInteger;

/**
 * @author zg
 * @create 2020-03-28 23:13
 *
 * 超级次方[中等]
 *
 * 你的任务是计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
 *
 * 示例 1:
 * 输入: a = 2, b = [3]
 * 输出: 8
 *
 * 示例 2:
 * 输入: a = 2, b = [1,0]
 * 输出: 1024
 *
 */
public class Test372 {
    private int superPow(int a, int[] b) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            string.append(b[i]);
        }
        BigInteger bigInteger = new BigInteger(string.toString());
        int res = 1;
        while (bigInteger.compareTo(new BigInteger("0"))>0){
            bigInteger = bigInteger.subtract(new BigInteger("1"));
            res = (res*(a%1337))%1337;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] b = {3};
        int tres = new Test372().superPow(10,b);
        System.out.println(tres);
    }
}
