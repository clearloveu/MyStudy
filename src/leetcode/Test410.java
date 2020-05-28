package leetcode;

/**
 * @author zg
 * @create 2020-04-24 11:50
 *
 * 分割数组的最大值[困难]
 *
 * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
 *
 * 注意:
 * 数组长度 n 满足以下条件:
 *
 * 	1 ≤ n ≤ 1000
 * 	1 ≤ m ≤ min(50, n)
 *
 * 示例:
 * 输入:
 * nums = [7,2,5,10,8]
 * m = 2
 *
 * 输出:
 * 18
 *
 * 解释:
 * 一共有四种方法将nums分割为2个子数组。
 * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 *
 *
 */
public class Test410 {
    // 动态规划
    public int splitArray(int[] nums, int m) {
        // dp[i][j]代表 nums从0到i的值被分成j份后，子数组和的最大值的最小值
        // 递推方程
        // dp[i][j] = max(dp[k][j - 1], nums[k + 1] + ... + nums[i])，k<i；而在这k种情况中求一个最小值
        // 因为dp保持的是最优子结构，所以dp[i][j]考虑的值只有2种情况，前一个j-1结构的最大值（已经保持了前j-1个的最大值），和当前第j个子数组的值
        int[][] dp = new int[nums.length+1][m+1];
        for (int i = 0; i < nums.length+1; i++) {
            for (int j = 0; j < m+1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        int[] sum = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        dp[0][0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sum[i] - sum[k]));
                }
            }
        }
        return dp[nums.length][m];

    }

    // 二分法
    public int splitArray2(int[] nums, int m) {
        long l = 0;
        long r = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            r += nums[i];
            if (l < nums[i]) {
                l = nums[i];
            }
        }
        long ans = r;
        while (l <= r) {
            long mid = (l + r) >> 1;
            long sum = 0;
            int cnt = 1;
            for (int i = 0; i < n; i++) {
                if (sum + nums[i] > mid) {
                    cnt ++;
                    sum = nums[i];
                } else {
                    sum += nums[i];
                }
            }
            if (cnt <= m) {
                ans = Math.min(ans, mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int)ans;
    }

}
