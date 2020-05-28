package leetcode;

/**
 * @author zg
 * @create 2020-02-07 19:24
 *
 * 零钱兑换[中等]
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 *
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 */
public class Test322 {
    //动态规划
    private static int coinChange(int[] coins, int amount) {
        //特例
        if (amount==0) return 0;
        //dq数组长度amount+1，因为计算逻辑从1开始，这样好理解
        int[] dq = new int[amount+1];
        //初始化dq数组每个元素为-1
        for(int i=0;i<=amount;i++){
            dq[i] = -1;
        }
        //状态转移方程：dq[n] = 1+min(dq[n-i]) for i in coins
        //从1开始
        for(int i =1;i<=amount;i++){
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

        return dq[amount];
    }

    public static void main(String[] args) {
        int[] coins = {2};
        int amount = 3;
        System.out.println(coinChange(coins,amount));
    }


}
