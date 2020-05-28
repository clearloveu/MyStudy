package leetcode;

/**
 * @author zg
 * @create 2020-03-28 14:18
 *
 * 编辑距离[困难]
 *
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 *
 * 	插入一个字符
 * 	删除一个字符
 * 	替换一个字符
 *
 *
 */
public class Test72 {
    private int minDistance(String word1, String word2) {

        if(word1.length()*word2.length()==0) return word1.length()+word2.length();

        // 字符串的dp方法，2种，见领扣笔记
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        //初始化
        for (int i=0;i<dp.length;i++){
            dp[i][0] = i;
        }
        for (int i=0;i<dp[0].length;i++){
            dp[0][i] = i;
        }

        // 转移方程：dp[i][j] = min(dp[i][j-1]+1,dp[i-1][j]+1,dp[i-1][j-1](+1)(取决于当前字符是否相等))
        for (int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if (word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j] = Math.min(dp[i][j-1]+1,Math.min(dp[i-1][j]+1,dp[i-1][j-1]));
                }else dp[i][j] = Math.min(dp[i][j-1]+1,Math.min(dp[i-1][j]+1,dp[i-1][j-1]+1));
            }
        }

        return dp[word1.length()][word2.length()];
    }





}
