package leetcode;

/**
 * @author zg
 * @create 2020-02-09 15:18
 *
 * 最佳买卖股票时机含冷冻期[中等]               [未完成]
 *
 *
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 	你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 	卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 *
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class Test309 {
    //错误解法，正确想法详情见有道云笔记 领扣-动态规划+博弈-Test309股票问题
    private int maxProfit(int[] prices) {
        int profile = 0;
        int date = 0;
        while(true){
            int buyDate = findBuyDate(prices,date);
            if(buyDate==-1) break;
            int sellDate = findSellDate(prices,buyDate);
            profile = profile+ prices[sellDate]-prices[buyDate];
            date = sellDate+2;
        }

        return profile;
    }

    //寻找合适的买入时机
    private int findBuyDate(int[] prices, int date){
        //如果传进来的date是最后一天
        if(date>=prices.length-1) return -1;
        while(true){
            //保证date+1不超出数组边界
            if(date>=prices.length-2) break;
            if(prices[date]<=prices[date+1]) return date;
            else date += 1;
        }
        //此时date是倒数第2天，可能是买入点
        return prices[date]<prices[date+1]?date:-1;
    }


    //寻找合适的卖出时机
    private int findSellDate(int[] prices, int buyDate){
        //如果传进来的buyDate是最后一天
        if(buyDate==prices.length-1) return buyDate;
        //如果传进来的buyDate是倒数第2天,则直接返回最后一天，因为在寻找合适的买入时机时，是买入时机的必要条件是后一天的价格不比前一天低
        if(buyDate==prices.length-2) return buyDate+1;

        int date = buyDate;
        while(true){
            //保证date+2不超出边界
            if(date>=prices.length-3) break;
            if(prices[date]>prices[date+2]) return date;
            else date+=1;

        }
        //此时date是倒数第3天
        //找到倒数3天中价格最大的一天
        int maxValueDate = prices[date]>=prices[date+1]?(prices[date]>=prices[date+2]?date:date+2):prices[date+1]>=prices[date+2]?date+1:date+2;
        if(prices[buyDate]<prices[maxValueDate]) return maxValueDate;
        else return buyDate;
    }
}
