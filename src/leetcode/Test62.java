package leetcode;

/**
 * @author zg
 * @create 2019-12-23 17:43
 *
 * 不同路径[中等]
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 * 说明：m 和 n 的值均不超过 100。
 *
 * 示例 1:
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 *
 * 示例 2:
 * 输入: m = 7, n = 3
 * 输出: 28
 *
 */
public class Test62 {

    private static int uniquePaths(int m, int n) {
        if (m==1||n==1) return 1;
        int[][] dp = new int[m][n];
        //初始化
        for (int i = 0; i < m; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = i;
        }
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }


    //领扣思路：动态规划
    //我们令 dp[i][j] 是到达 i, j 最多路径
    //动态方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
    //注意，对于第一行 dp[0][j]，或者第一列 dp[i][0]，由于都是在边界，所以只能为 1
    //时间复杂度：O(m∗n)
    //空间复杂度：O(m∗n)
    //优化：因为我们每次只需要 dp[i-1][j],dp[i][j-1]

    public static void main(String[] args) {
        int m = 9;
        int n = 3;
        System.out.println(uniquePaths(m,n));
    }
}
