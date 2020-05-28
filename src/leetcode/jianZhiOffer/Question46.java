package leetcode.jianZhiOffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zg
 * @create 2020-03-12 16:22
 *
 * 把数字翻译成字符串[中等]
 *
 */
public class Question46 {
    private int translateNum(int num) {
        if(num==0) return 1;
        // 动态规划
        // 状态：num的索引，值：该索引对应的翻译方法的数量
        List<Integer> numList = new ArrayList<>();
        while(num>0){
            numList.add(0,num%10);
            num = num/10;
        }
        // 从后往前（等价于从前往后推）
        // 递推式：dp[i] = dp[i+1] + dp[i+2]或者0
        int[] dp = new int[numList.size()+1];
        dp[numList.size()] = 1;  //为了兼任递推式：12--> 2种:在遍历到1时，dp[0] = dp[1] + dp[2];
        dp[numList.size()-1] = 1;
        for(int i=numList.size()-2;i>=0;i--){
            int temp = numList.get(i)*10+numList.get(i+1);
            if(temp>25 || numList.get(i)==0) dp[i] = dp[i+1];
            else dp[i] = dp[i+1] + dp[i+2];
        }
        return dp[0];


        // 从前往后推是一样的
//        int[] dp = new int[numList.size()+1] ;
//        dp[0] = 1;//不代表任何意义，为了兼荣递推式
//        dp[1] = 1;//第一个字符只能有一种翻译方法
//        // 递推式：
//        // dp[i] = dp[i-1] + dp[i-2]或者0
//        for(int i = 2;i<numList.size()+1;i++){
//            int temp = numList.get(i-2)*10+numList.get(i-1);
//            if(temp>25 || numList.get(i-2)==0) dp[i] = dp[i-1];
//            else dp[i] = dp[i-1]+dp[i-2];
//        }
//
//        return dp[numList.size()];


    }
}
