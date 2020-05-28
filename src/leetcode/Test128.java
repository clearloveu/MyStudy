package leetcode;

import java.util.Arrays;

/**
 * @author zg
 * @create 2020-03-28 22:42
 *
 * 最长连续序列[困难]
 *
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 *
 */
public class Test128 {
    // 未实现时间复杂度为 O(n)
    public int longestConsecutive(int[] nums) {
        if(nums.length==0) return 0;

        Arrays.sort(nums);
        int res = 1;
        int max = 1;
        for (int i=1;i<nums.length;i++){
            if(nums[i]==nums[i-1]) continue;
            if (nums[i]-nums[i-1]==1){
                res++;
            }else {
                max = Math.max(res,max);
                res = 1;
            }
            max = Math.max(res,max);
        }
        return max;

    }

    // 领扣思路： HashSet保存nums中每一个元素，再去找最长的连续子序列


}
