package leetcode.jianZhiOffer;

/**
 * @author zg
 * @create 2020-04-06 10:33
 *
 * 面试题16. 数值的整数次方[中等]
 *
 * 实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
 *
 * 示例 1:
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 *
 * 示例 3:
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 *
 */
public class Question16 {
    // O(n)复杂度
//    private double myPow(double x, int n) {
//        int flag = (int)Math.abs(n);
//        double res = 1.0;
//        while (flag!=0) {
//            flag--;
//            res *= x;
//        }
//        return n>0?res:n==0?1:1/res;
//    }

    // O(log n)复杂度
    public double myPow(double x, int n) {
        if(n==0) return 1.0;
        double res = 1.0;
        // 这里不能使用int，因为n如果等于Integer.MIN_VALUE的话，-n还是Integer.MIN_VALUE,这里需要注意，要用long
//        int m = Math.abs(n);
        // 这样使用long是错误的，因为-n仍然有问题
//        long m = n;
//        if (n<0) m = -n;
        long m = n;
        if (n<0) m = -m;

        while (m!=1){
            if (m%2==1) res*=x;
            x *=x;
            m /=2;
        }
        return n>0?res*x:1/(res*x);

    }

}
