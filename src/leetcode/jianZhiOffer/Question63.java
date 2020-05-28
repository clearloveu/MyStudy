package leetcode.jianZhiOffer;

/**
 * @author zg
 * @create 2020-03-14 17:46
 *
 * 股票的最大利润[中等]
 *
 */
public class Question63 {
    //动态规划
    private int maxProfit(int[] prices) {
        if(prices.length==0) return 0;
        int[][] dp = new int[prices.length][2];

        // dp[0][0] = -prices[0];
        // dp[0][1] = Integer.MIN_VALUE;
        //优化
        int buy = -prices[0];
        int sell = Integer.MIN_VALUE;
        for(int i =1;i<prices.length;i++){
            int todayBuy = Math.max(-prices[i],buy);
            int todaySell = Math.max((buy+prices[i]),sell);
            buy = todayBuy;
            sell = todaySell;
        }
        // return dp[prices.length-1][1]>0?dp[prices.length-1][1]:0;
        return sell>0?sell:0;

//        //不使用动态规划，参考剑指offer
//        int min = Integer.MAX_VALUE;
//        int profit = 0;
//        for(int i=0;i<prices.length;i++){
//            min = Math.min(min,prices[i]);
//            profit = Math.max(profit,prices[i]-min);
//        }
//        return profit;
    }


}
