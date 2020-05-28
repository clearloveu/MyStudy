package leetcode;

/**
 * @author zg
 * @create 2020-02-14 14:59
 *
 * 和为K的子数组[中等]
 *
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 *
 * 说明 :
 * 	数组的长度为 [1, 20,000]。
 * 	数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 *
 */
public class Test560 {
    //领扣，使用哈希表可以在O(n)时间内解决
    private int subarraySum(int[] nums, int k) {

        //O(n^2)
        int answer = 0;

        //状态：索引i，连续子数组的第一个元素j，值代表以从第j个元素开始，第i个元素结尾的和
        //第i个元素结尾的连续子数组的个数为i个，故j最多有nums.length个
        // int[][] dp = new int[nums.length][nums.length];
        // //状态转移方程
        // //dp[i][j] = dp[i-1][j]+nums[i]
        // //初始化
        // dp[0][0] = nums[0];
        // if(dp[0][0]==k) answer+=1;
        // for(int i=1;i<nums.length;i++){
        //     for(int j=0;j<=i;j++){
        //         dp[i][j] = dp[i-1][j]+nums[i];
        //         if(dp[i][j]==k) answer +=1;
        //     }
        // }


        //优化
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        if(dp[0]==k) answer+=1;
        for(int i=1;i<nums.length;i++){
            for(int j=0;j<=i;j++){
                dp[j] = dp[j]+nums[i];
                if(dp[j]==k) answer +=1;
            }
        }

        return answer;
    }
}
