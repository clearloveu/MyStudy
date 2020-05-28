package leetcode.jianZhiOffer;

/**
 * @author zg
 * @create 2020-02-23 17:11
 *
 * 剪绳子[中等]
 *
 */
public class Question14_1 {
    //动态规划(参考剑指offer)
    private int cuttingRope(int n) {
        if(n<2) return 0;
        else if(n==2) return 1;
        else if(n==3) return 2;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for(int index = 4;index<=n;index++){
            int temp = 0;
            //递推式：f(index) = max(f(i)Xf(index-i)),0<i<index
            for(int i=1;i<index;i++){
                temp = Math.max(dp[i]*dp[index-i],temp);
            }
            dp[index] = temp;
        }
        return dp[n];
    }
    //参考题解
    //https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/solution/mian-shi-ti-14-ii-jian-sheng-zi-iitan-xin-er-fen-f/
    private int cuttingRope2(int n) {
        //贪婪算法
        if (n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if (b == 0) return (int) Math.pow(3, a);
        if (b == 1) return (int) Math.pow(3, a - 1) * 4;
        return (int) Math.pow(3, a) * 2;
    }
}
