package leetcode.jianZhiOffer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zg
 * @create 2020-02-20 15:31
 *
 * 找出数组中重复的数字。[简单]
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
 * 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 * 限制：
 * 2 <= n <= 100000
 */
public class Question3 {
    private int findRepeatNumber(int[] nums) {
        // 参考287. 寻找重复数

        //最简单的Hash判断
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<nums.length;i++){
            if(!set.contains(nums[i])){
                set.add(nums[i]);
            }else return nums[i];
        }
        return -1;

        //排序
        //使用二分查找法定位在一个区间里的整数（结合抽屉原理）（O(nlogn)）：
        //快慢指针，循环检测
    }
}
