package leetcode;

/**
 * @author zg
 * @create 2019-12-16 20:59
 *
 * 爬楼梯[简单]
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 *  注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 *
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 */
public class Test70 {
    private static int climbStairs(int n) {

        //特例
        if (n==1) return 1;
        if (n==2) return 2;

//        //斐波那契数的思想
//        //递推公式f(n) = f(n-1) + f(n-2)
//        //自底而上，动态规划思想
//        //f(1)+f(2)=f(3),f(2)+f(3)=f(4),........
//        //动态规划思想常用数组，dq数组
//        int[] dq = new int[n];
//        dq[0] = 1;
//        dq[1] = 2;
//        for (int i = 2;i<n;i++){
//            dq[i] = dq[i-1] +dq[i-2] ;
//        }
//        return dq[n-1];


        //为了减小内存消耗，只需要记录3个数字即可递推，无需dq数组
        if (n==3) return 3;
        int current_before2 = 1;
        int current_before1 = 2;
        int current = current_before1+current_before2;
        for (int i=3;i<n;i++){
            current_before2 = current_before1;
            current_before1 = current;
            current = current_before1+current_before2;
        }

        return current;
    }

    public static void main(String[] args) {
        int n = 11;
        System.out.println(climbStairs(n));
    }
}
