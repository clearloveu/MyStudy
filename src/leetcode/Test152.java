package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zg
 * @create 2020-01-07 9:33
 *
 * 乘积最大子序列[中等]                                [未完成]
 *
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 *
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 *
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 *
 */

public class Test152 {

//    标签：动态规划
//    遍历数组时计算当前最大值，不断更新
//    令imax为当前最大值，则当前最大值为 imax = max(imax * nums[i], nums[i])
//    由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值imin，imin = min(imin * nums[i], nums[i])
//    当负数出现时则imax与imin进行交换再进行下一步计算
//    时间复杂度：O(n)
//
//    作者：guanpengchn
//    链接：https://leetcode-cn.com/problems/maximum-product-subarray/solution/hua-jie-suan-fa-152-cheng-ji-zui-da-zi-xu-lie-by-g/

    //imax表示以当前节点为终结节点的最大连续子序列乘积 imin表示以当前节点为终结节点的最小连续子序列乘积
    //和 53 题 子数组最大的和思路差不多。
    private static int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for(int i=0; i<nums.length; i++){
            if(nums[i] < 0){
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax*nums[i], nums[i]);
            imin = Math.min(imin*nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }


    //我想到的思路：https://leetcode.wang/leetcode-152-Maximum-Product-Subarray.html




    //找到原数组中所有0的位置
    private static List<Integer> findZero(int[] nums){
        List<Integer> zeros = new ArrayList<>();
        for (int i = 0;i<nums.length;i++){
            if (nums[i]==0) zeros.add(i);
        }
        return zeros;
    }
    //找到给定2个位置中间的负数的数量、最右边负数和最左边负数位置
    private static List<Integer> findFuShu(int startIndex,int lastIndex,int[] nums){
        List<Integer> fushus = new ArrayList<>();
        int fuShuNumber = 0;
        int fushuLeftest = -1 ;
        int fushuRightest = -1;
        for (int i = startIndex;i<lastIndex;i++){
            if (nums[i]<0) {
                fuShuNumber+=1;
                if (fushuLeftest==-1) fushuLeftest = i;
                fushuRightest = i;
            }
        }
        fushus.add(fuShuNumber);
        fushus.add(fushuLeftest);
        fushus.add(fushuRightest);
        return fushus;
    }



    public static void main(String[] args) {
        int[] nums = {2,3,-2,4};
        System.out.println(maxProduct(nums));

    }

}

//    private static int maxProduct(int[] nums) {
//        //zeros为所有0的位置
//        List<Integer> zeros = findZero(nums);
//
//        int maxchengji = 0;
//            for (int i = 0;i<=zeros.size();i++){
//                    int startIndex = 0;
//                    int lastIndex = 0;
//                    if (i==0) lastIndex = nums[zeros.get(i)];
//                    else if (i==nums.length) {
//                    startIndex = nums[zeros.get(i-1)];
//                    lastIndex = nums.length-1;
//                    }else {
//                    startIndex = nums[zeros.get(i-1)];
//                    lastIndex = nums[zeros.get(i)];
//                    }
//                    List<Integer> fushus = findFuShu(startIndex,lastIndex,nums);
//            if (fushus.get(0)%2==0){
//            int temp = 0;
//            }
//
//
//
//
//            }
//            return maxchengji;
//    }


//    //回溯法：
//    //最大乘积
//    int maxChengji  = 0;
//    //当前最大乘积
//    int maxChengjiTemp = 0;
//    //最左边的负数的序号
//    int firsrFushuIndex = -1;
//    //当前遍历的序号
//    int currentIndex = 0;
////找到第一个非0元素赋给当前最大乘积
//        while (true){
//                if (currentIndex==nums.length-1) break;
//                if (nums[currentIndex]!=0) {
//                maxChengjiTemp = nums[currentIndex];
//                break;
//                }
//                currentIndex +=1;
//                }
//                if (maxChengjiTemp==0) return 0;
//
//
//
//                while (true){
//
//
//
//                if (nums[currentIndex]!=0){
//                maxChengji = maxChengji*nums[currentIndex];
//                }
//
//
//
//                currentIndex += 1;
//                }
//
//
//                return maxChengji;