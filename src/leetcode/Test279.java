package leetcode;

import java.util.ArrayList;
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
        int answer = new Test279().numSquares(12);
        System.out.println(answer);
    }
}
