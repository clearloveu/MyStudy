package com.zs;

/**
 * @author zg
 * @create 2019-12-23 18:06
 */
class Solution {
//    static int answer = 0;
//    public int uniquePaths(int m, int n) {
//        recursion(m-1,n-1);
//        return answer;
//    }
//
//    synchronized private static void recursion(int m , int n){
//        //递归基
//        if (m==0&&n==0){
//            answer +=1;
//            return;
//        }
//        //递归体
//        if (m==0){
//            recursion(m,n-1);
//        }else if (n==0){
//            recursion(m-1,n);
//        }else {
//            recursion(m-1,n);
//            recursion(m,n-1);
//        }
//    }

//    public int uniquePaths(int m, int n) {
//        int[][] dp = new int[m][n];
//        for (int i = 0; i < n; i++) dp[0][i] = 1;
//        for (int i = 0; i < m; i++) dp[i][0] = 1;
//        for (int i = 1; i < m; i++) {
//            for (int j = 1; j < n; j++) {
//                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
//            }
//        }
//        return dp[m - 1][n - 1];
//    }



    public static void main(String[] args) {

//            System.out.println(new Solution().uniquePaths(9,3));
    }
}
