package leetcode;

import java.util.Arrays;

/**
 * @author zg
 * @create 2020-02-13 16:45
 *
 * 分割等和子集[中等]
 *
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 * 	每个数组中的元素不会超过 100
 * 	数组的大小不会超过 200
 *
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *
 * 示例 2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 *
 */
public class Test416 {

    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).reduce((a, b) -> a+ b).orElse(-1);
        if(sum < 0 || (sum & 1) == 1) return false;
        sum = sum >> 1;

        boolean[][] dp = new boolean[nums.length + 1][sum +1];
        dp[0][0] = true;
        for(int i = 1;i <nums.length +1;i++) {
            for(int j = 1;j<sum+1;j++) {
                if(j < nums[i-1]) dp[i][j] = dp[i-1][j];
                else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[nums.length][sum];
    }




    private boolean canPartition2(int[] nums) {
        //特例
        if(nums.length==1) return false;

        // 0/1背包问题
        //求总和
        int sum =0;
        for(int temp:nums){
            sum +=temp;
        }
        //如果和是单数，不可能分割成功
        if((sum&1)==1) return false;
        int target = sum/2;


//        //状态：第几个元素，当前和大小
//        int[][] dp = new int[nums.length][target+1];//dp[i][j]:第i个元素和为j是否有可能，值1代表有可能，0代表没可能
//        dp[0][nums[0]] =1;//代表取第1个数
//        //递推方程
//        // dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]]
//        for(int i=1;i<nums.length;i++){
//            for(int j=0;j<=target;j++){
//                if(j>=nums[i]) dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]];
//                else dp[i][j] = dp[i-1][j];
//                if(j==target && dp[i][j]!=0) return true;
//            }
//        }
//        return false;

        //dp数组优化：共享一维dp（第i次循环的判断只与第i-次循环的结果相关）
        //状态：第几个元素，当前和大小
        int[]dp = new int[target+1];//dp[j]:和为j是否有可能，值1代表有可能，0代表没可能
        dp[nums[0]] =1;//代表取第1个数
        //递推方程
        // dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]]
        for(int i=1;i<nums.length;i++){
            for(int j=target;j>=nums[i];j--){
                dp[j] = dp[j] + dp[j-nums[i]];
                if(j==target && dp[j]!=0) return true;
            }
        }
        return false;
        //细节：，0/1背包问题中，二维dp变成一维dp，产生的影响是倒序，因为这里共享一维dp，在更新第i次循环时，它会在第i-1次循环产生的
        // dp上直接修改数值，但是在第i次循环中又需要第i-1次循环的dp数组，所以这里需要考虑怎么才能在更新共享dp数组的同时又不影响接下来的判断，答案是倒序。
    }

    public static void main(String[] args) {
        int[] nums = {1,5,11,5};
        new Test416().canPartition(nums);
    }
}
