package leetcode;

/**
 * @author zg
 * @create 2019-12-12 13:31
 *
 * 搜索旋转排序数组[中等]
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 *
 *  示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 */
public class Test33 {
    public static  int search(int[] nums, int target) {
        int answer = -1;

        if (nums.length==0) return -1;

        //特例：旋转点是第0个点，即[1,2,3]--->[1,2,3]
        if (nums[0]<=nums[nums.length-1]){
            return dichotomy(nums,0,nums.length-1,target);
        }


        //双指针
        int left = 0 ;
        int right = nums.length-1;
        //类似二分法寻找旋转点
        int media = (left+right)/2;
        while (media!=left && media!= right){
            if (nums[left]<nums[media]) left = media;
            else right = media;
            media = (left+right)/2;
        }
        //旋转点，即数组中最大的数的秩
        int boundary = nums[left]<nums[right]?left-1:left;

        if (target>=nums[0]){
            //在旋转点前面的数组进行二分法搜索
            answer = dichotomy(nums,0,boundary,target);
        }else {
            //在旋转点后面的数组进行二分法搜索
            answer = dichotomy(nums,boundary+1,nums.length-1,target);
        }
        return answer ;
    }

    public static int dichotomy(int[] nums,int left,int right,int target){
        while (left<right){
            int media = (left+right)/2;
            if (nums[media]<target){
                left = media+1;
            }else if (nums[media]>target){
                right = media;
            }else return media;
        }
        return nums[left]==target?left:-1;
    }




    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        int[] nums2 = {1,3};
        int target2 = 3;
        int[] nums3 = {3,1};
        int[] nums4 = {4,5,6,7,0,1,2};
        System.out.println(search(nums4,target2));

    }
}
