package leetcode;

/**
 * @author zhaoguang
 * @create 2024-06-14 00:00
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 *
 */
public class Test35 {
    // 错误解法
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid]==target) return mid;
            else if(nums[mid] < target){
                left = mid+1;
            } else {
                right = mid -1;
            }
        }
        // left = 0,right = -1
        // left = 4,right = 3

        // 如果没有找到target，会出现right比left小1的情况而退出循环，此时right就是小于target的最大数字的索引，left就是大于target的最小数字的索引。
        return left > nums.length-1 ? left :  nums[left] > target ? left :left +1;
    }

    // 试试寻找左界
    private int searchInsert2(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (left == nums.length) return left;
        // 类似之前算法的处理方式
        return left;
    }

}
