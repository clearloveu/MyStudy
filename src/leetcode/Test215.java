package leetcode;

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
    //方法一：快排思想
    //方法二：堆的思想(优先队列PriorityQueue，参考有道云笔记)
    private static int findKthLargest(int[] nums, int k) {
        // Arrays.sort(nums);
        // return nums[nums.length-k];

        int rightIndex = nums.length-k;
        int index = partition(nums,0,nums.length-1);
        while (index!=rightIndex){
            if (index>rightIndex){
                index = partition(nums,0,index-1);
            }else index = partition(nums,index+1,nums.length-1);
        }
        return nums[rightIndex];
    }

    private static  int partition(int[] nums, int start, int end){
        //随机取一个轴点
        int randomIndex = (int)(Math.random()*(end-start)+start);
        int pivot = nums[randomIndex];
        nums[randomIndex] = nums[start];
        nums[start] = pivot;


        //参考快速排序的思想
        while (start<end){
            while ((start<end && pivot<=nums[end])) end -=1;
            nums[start] = nums[end];
            while (start<end && pivot>=nums[start]) start +=1;
            nums[end] = nums[start];
        }
        nums[start] = pivot;
        return start;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        int k = 3;

        int[] nums2 = {3,2,3,1,2,4,5,5,6};
        int k2  =4;

        System.out.println(findKthLargest(nums2,k2));
    }

}
