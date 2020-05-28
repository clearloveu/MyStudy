package leetcode.jianZhiOffer;

/**
 * @author zg
 * @create 2020-03-12 17:46
 *
 * 礼物的最大价值[中等]
 *
 */
public class Question47 {
    private int maxValue(int[][] grid) {
        //动态规划
        //状态：i，j：棋盘对应的位置，值：对应到此步的最大价值
        // 递推方程：dp[i][j] = max(dp[i-1][i],dp[i][j-1])+grid[i][j]
        if(grid.length==0) return 0;
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for(int j = 1;j<grid[0].length;j++){
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        for(int i =1 ;i<grid.length;i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for(int i=1;i<grid.length;i++){
            for(int j=1;j<grid[0].length;j++){
                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[grid.length-1][grid[0].length-1];

    }
}
