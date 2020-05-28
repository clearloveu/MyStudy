package leetcode.jianZhiOffer;

/**
 * @author zg
 * @create 2020-04-06 10:32
 *
 * 面试题15. 二进制中1的个数[简单]
 *
 * 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。
 *
 * 例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
 *
 *
 */
public class Question15 {
    // you need to treat n as an unsigned value
    private int hammingWeight(int n) {
        int num = 0;
        while(n!=0){
            n = n&(n-1);
            num++;
        }
        return num;
    }
}
