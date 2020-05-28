package leetcode;

/**
 * @author zg
 * @create 2020-02-09 15:21
 *
 * 买卖股票的最佳时机含手续费[中等]
 *
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 *
 * 你可以无限次地完成交易，但是你每次交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 示例 1:
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 *
 * 注意:
 * 	0 < prices.length <= 50000.
 * 	0 < prices[i] < 50000.
 * 	0 <= fee < 50000.
 *
 *
 */
public class Test714 {
    private int maxProfit(int[] prices, int fee) {
        //步骤参考有道云笔记 领扣-动态规划
        //2个状态,天数+持有（不持有）-->dp[day][m],m=0为不持有，m=1为持有

        // //状态---->dp数组
        // int[][] dp = new int[prices.length][2];
        // //状态转移图：脑补
        // //状态转移方程1：m=0为不持有，m=1为持有
        // // dp[i][0] = max(dp[i-1][0],dp[i-1][1]+prices[i]-fee)
        // // dp[i][1] = max(dp[i-1][1],dp[i-1][0]-prices[i])
        // //如果是不可能发生的情况，取最小值，在迭代的时候取max的时候就会舍去这种情况
        // dp[0][0] = 0;
        // dp[0][1] = -prices[0];//利润是负的
        // for(int i =1;i<prices.length;i++){
        //     dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]-fee);
        //     dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
        // }
        // return dp[prices.length-1][0];

        //优化：只用2个变量
        int dp_pre_0 = 0;
        int dp_pre_1 = -prices[0];
        for(int i =1;i<prices.length;i++){
            int dp_now_0 = Math.max(dp_pre_0,dp_pre_1+prices[i]-fee);
            int dp_now_1 = Math.max(dp_pre_1,dp_pre_0-prices[i]);
            dp_pre_0 = dp_now_0;
            dp_pre_1 = dp_now_1;
        }
        return dp_pre_0;
    }
}
