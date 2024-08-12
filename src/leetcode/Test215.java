package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zg
 * @create 2020-01-10 19:04
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 *
 */
public class Test215 {
    //堆的思想
    private static int findKthLargest2(int[] nums, int k) {
        Queue<Integer> q = new PriorityQueue<Integer>(k, (a, b) -> a >= b ? -1 : 1);
        Arrays.stream(nums).boxed().forEach(q::add);
        int res = 0;
        while (k > 0) {
            k--;
            res = q.poll();
        }
        return res;
    }

    //快排思想
    public static int findKthLargest3(int[] nums, int k) {
        return find(nums, 0, nums.length-1,k);
    }

    private static int find(int[] nums, int left, int right, int k) {
        int tempLeft = left;
        int tempRight = right;
        int pivotIndex = (int)(Math.random() * (right - left)) + left;
        int pivot = nums[pivotIndex];
        nums[pivotIndex] = nums[left];
        while(left < right) {
            while(left < right && pivot <= nums[right]) right--;
            nums[left] = nums[right];
            while(left < right && pivot >= nums[left]) left++;
            nums[right] = nums[left];
        }
        if(left == nums.length - k) return pivot;
        if(left > nums.length - k) return find(nums, tempLeft, left-1, k);
        return find(nums, left + 1, tempRight, k);
    }


    // 桶排序
    public int findKthLargest(int[] nums, int k) {
        int[] buckets = new int[20001];
        for (int i = 0; i < nums.length; i++) {
            buckets[nums[i] + 10000]++;
        }
        for (int i = 20000; i >= 0; i--) {
            k = k - buckets[i];
            if (k <= 0) {
                return i - 10000;
            }
        }
        return 0;
    }



    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        int k = 5;

        int[] nums2 = {3,2,3,1,2,4,5,5,6};
        int k2  =4;

        System.out.println(findKthLargest3(nums,k));
    }

}
