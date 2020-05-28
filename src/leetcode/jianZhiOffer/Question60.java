package leetcode.jianZhiOffer;

import java.util.Arrays;

/**
 * @author zg
 * @create 2020-03-13 12:40
 *
 * n个骰子的点数[简单]
 *
 */
public class Question60 {
    // 参考：https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof/solution/nge-tou-zi-de-dian-shu-dong-tai-gui-hua-ji-qi-yo-3/
    // class Solution {
    //    public double[] twoSum(int n) {
    //        int [][]dp = new int[n+1][6*n+1];
    //        //边界条件
    //        for(int s=1;s<=6;s++)dp[1][s]=1;
    //        for(int i=2;i<=n;i++){
    //            for(int s=i;s<=6*i;s++){
    //                //求dp[i][s]
    //                for(int d=1;d<=6;d++){
    //                    if(s-d<i-1)break;//为0了
    //                    dp[i][s]+=dp[i-1][s-d];
    //                }
    //            }
    //        }
    //        double total = Math.pow((double)6,(double)n);
    //        double[] ans = new double[5*n+1];
    //        for(int i=n;i<=6*n;i++){
    //            ans[i-n]=((double)dp[n][i])/total;
    //        }
    //        return ans;
    //    }
    //}
    private double[] twoSum(int n){
        int[] point = new int[6*n+1];
        int[] point2  = new int[6*n+1];
        boolean flag = true;
        // 1个筛子
        for(int i=1;i<=6;i++) point[i]=1;
        for(int i=1;i<n;i++){
            if(flag){
                Arrays.fill(point2,0);
                for(int j=1;j<=6;j++){
                    for(int k=1;k<=6*n;k++){
                        if(point[k]!=0){
                            point2[k+j] = point2[k+j]+point[k];
                        }
                    }
                }
                flag = false;
             }
             else {
                Arrays.fill(point,0);
                for(int j=1;j<=6;j++){
                    for(int k=1;k<=6*n;k++){
                        if(point2[k]!=0){
                            point[k+j] = point[k+j]+point2[k];
                        }
                    }
                }
                flag = true;
             }
        }

        double[] res = new double[6*n-n+1];
        // 总共6^n次方种可能
        double sum = Math.pow(6,n);
        for(int i=n;i<=6*n;i++){
            if (flag) res[i-n] = point[i]/sum;
            else res[i-n] = point2[i]/sum;
        }

        return res;

    }

    public static void main(String[] args) {
        new Question60().twoSum(2);
    }
}
