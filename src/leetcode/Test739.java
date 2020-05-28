package leetcode;

/**
 * @author zg
 * @create 2020-02-15 13:40
 *
 * 每日温度[中等]
 *
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 *
 */
public class Test739 {
    private int[] dailyTemperatures(int[] T) {
        // 状态：天 dp[i] 代表第i天等待多久温度才会升高超过该日的天数
        // 转移方程： dp[i] = 1+dp[i+1]+dp[j]  或者 1 或者 0
        // 解释： 如果后一天温度大于当天，则dp[i] = 1;
        // 如果后一天温度小于等于当天，则比较第一次超过后一天的那一天j的温度和当天的温度，
        // 如果小于等于，继续比较，直到m某一次dp[j]=0，则dp[i]=0,否则在某一次比较中返回dp[j]

        int[] dp = new int[T.length];
        dp[T.length-1] = 0;
        for(int i=T.length-2;i>=0;i--){
            //距离第i天的天数
            int days = 1;
            int temp = T[days+i]; //第(days+i)天的温度
            //当某一天的温度大于第i天的温度 或者已经找到第i天后面温度最大的某一天（此时dp[i+days]==0）
            while(T[i]>=temp && dp[i+days]!=0){
                days += dp[i+days]; //找到第一次超过第days+i天的温度的一天
                temp = T[days+i];
            }
            //如果第(days+i)天的温度确实大于第i天温度，则days，否则为0，代表第i天为第i天后面的最大温度的一天
            dp[i] = temp>T[i]?days:0;
        }
        return dp;
    }
}
