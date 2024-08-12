package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zg
 * @create 2020-02-18 15:13
 *
 * 完全平方数[中等]
 *
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 *
 * 示例 2:
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 */
public class Test279 {
    int INF = -1;
    public int numSquares4(int n) {
        // 预处理出所有可能用到的「完全平方数」
        List<Integer> list = new ArrayList<>();
        int idx = 1;
        while (idx * idx <= n) {
            list.add(idx * idx);
            idx++;
        }

        // f[i][j] 代表考虑前 i 个物品，凑出 j 所使用到的最小元素个数
        int len = list.size();
        int[][] f = new int[len+1][n + 1];

        // 处理第一个数的情况
        for (int j = 0; j <= n; j++) {
            int t = list.get(0);
            int k = j / t;
            if (k * t == j) { // 只有容量为第一个数的整数倍的才能凑出
                f[1][j] = k;
            } else { // 其余则为无效值
                f[1][j] = INF;
            }
        }
        System.out.println(Arrays.deepToString(f));

        // 处理剩余数的情况
        for (int i = 2; i <= len; i++) {
            int t = list.get(i-1);
            for (int j = 0; j <= n; j++) {
                // 对于不选第 i 个数的情况
                f[i][j] = f[i - 1][j];
                // 对于选 k 次第 i 个数的情况
                for (int k = 1; k * t <= j; k++) {
                    // 能够选择 k 个 t 的前提是剩余的数字 j - k * t 也能被凑出
                    // 使用 0x3f3f3f3f 作为最大值（预留累加空间）可以省去该判断
                    if (f[i - 1][j - k * t] != INF) {
                        f[i][j] = Math.min(f[i][j], f[i - 1][j - k * t] + k);
                    }
                }

            }
        }
        return f[len][n];
    }

    // 完全背包优化
    public int numSquares2(int n) {
        int[] dp = new int[n+1];
        // dp[i] = dp[i-x] +1;
        int index = 1;
        List<Integer> list = new ArrayList<>();
        for(int i = 1;i<=n;i++) {
            dp[i] = Integer.MAX_VALUE;
            if(i == index*index) {
                list.add(index*index);
                index++;
            }
            for(int temp :list) {
                // 最优的是，对于当前值，肯定由前x个值推导过来
                dp[i] = Math.min(dp[i-temp] + 1, dp[i]);
            }
        }
        return dp[n];
    }


    private int numSquares(int n) {
        //先找到小于等于n的所有完全平方数
        List<Integer> squ = new ArrayList<>();
        for(int i=1;;i++){
            if(i*i<=n) squ.add(i*i);
            else break;
        }
        //然后类似于零钱兑换，完全平方数就是硬币，n就是总金额，利用动态规划
        int[] coins = new int[squ.size()];
        for(int i = 0;i<squ.size();i++){
            coins[i] = squ.get(i);
        }
        int[] dq = new int[n+1];
        //初始化dq数组每个元素为-1
        for(int i=0;i<=n;i++){
            dq[i] = -1;
        }
        //状态转移方程：dq[n] = 1+min(dq[n-i]) for i in coins
        //从1开始
        for(int i =1;i<=n;i++){
            for(int j = 0;j<coins.length;j++){
                //如果现在总金额i小于该硬币的金额，略过
                if(i<coins[j]) continue;
                    //如果现在总金额i等于该硬币的金额,硬币个数为1
                else if(i==coins[j]) dq[i] = 1;
                else {
                    //如果现在总金额i大于该硬币的金额c，它现在需要的硬币数是(i-c)金额需要的硬币数+1
                    //如果(i-c)金额是-1，则加上该硬币也无法组成总金额i，略过
                    if (dq[i-coins[j]]==-1) continue;
                    else {
                        int temp = dq[i - coins[j]] + 1;
                        //如果还没有能组成该金额，直接赋值
                        if (dq[i] == -1) dq[i] = temp;
                            //否则，取最小硬币数量
                        else dq[i] = Math.min(temp, dq[i]);
                    }
                }
            }
        }
        return dq[n];
    }

    public static void main(String[] args) {
        int answer = new Test279().numSquares4(12);
        System.out.println(answer);
    }
}
