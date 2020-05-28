package niuke;

import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * 4.1携程笔试
 * leetcode 72 计算距离原题
 *
 */
public class Xiecheng3 {
    static int res;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
        String temp = in.nextLine();
        String[] dict = { "surprise", "happy", "ctrip", "travel", "wellcome","student","system","program","editor"};
        String ans = "null";
        for (int i = 0; i < dict.length; i++) {
            String res = minDistance(temp,dict[i]);
            if (!res.equals("null")) {
                ans = res;
                break;
            }

        }
        System.out.println(ans);


    }
    private static String minDistance(String word1, String word2) {


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
        if (dp[word1.length()][word2.length()]>2) return "null";
        else return word2;
    }
}
