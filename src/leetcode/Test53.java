package leetcode;

/**
 * @author zg
 * @create 2019-12-11 11:13
 *
 * 最大子序和[简单]
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 *  输入: [-2,1,-3,4,-1,2,1,-5,4],
 *  输出: 6
 *  解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 */
public class Test53 {
    public static  int maxSubArray(int[] nums) {

        //dq数组记录每一个元素的到此元素的最大和
        int[] dq = new int[nums.length];
        //初始化
        for (int i = 0 ; i<dq.length;i++){
            dq[i] = 0 ;
        }
        dq[0] = nums[0] ;
        for (int i = 1 ; i<nums.length;i++){
            if (dq[i-1]<=0){
                dq[i] = nums[i];
            }else if ( dq[i-1]>0 ){
                dq[i] = nums[i]+dq[i-1];
            }
        }

        int maxSum = Integer.MIN_VALUE;
        for (int i = 0 ; i<dq.length;i++){
            if (maxSum<dq[i]) maxSum= dq[i];
        }

        return maxSum;
    }

    //优化，没必要定义dq数组，直接一个变量表示即可
    public static int maxSubArray2(int[] nums) {

        int ans = nums[0];
        int sum = 0;
        for(int num: nums) {
            if(sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;

    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};

        System.out.println(maxSubArray2(nums));



    }
}
