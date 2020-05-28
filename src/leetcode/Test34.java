package leetcode;

import java.util.Arrays;

/**
 * @author zg
 * @create 2019-12-19 15:21
 *
 * 在排序数组中查找元素的第一个和最后一个位置[中等]
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 *
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 */
public class Test34 {
    private static int[] searchRange(int[] nums, int target) {
        int[] answer = new int[2];

        //特例：nums={}
        if (nums.length==0){
            answer[0] = -1;
            answer[1] = -1;
            return answer;
        }

        //二分法
        int left = 0;
        int right = nums.length-1;
        int firstTarget = -1;
        while (left<right){
            int media = (left+right)/2;
            if (nums[media]<target){
                left = media+1;
            }else if (nums[media]>target){
                right = media;
            }else {
                firstTarget = media;
                break;
            }
        }
//        return nums[left]==target?left:-1;
        //如果未找到
        if (firstTarget==-1 &&  nums[left]!=target){
            answer[0] = -1;
            answer[1] = -1;
        }else if (firstTarget!=-1){
            //如果已经找到某一个target
            //为了追求O(logn)的效率，找开始和结尾也需要用类似二分法的方法去找
            //找开始,true代表往前找
            int start = TwoSearch(nums,firstTarget,true);
            //找结尾，false代表往后找
            int last = TwoSearch(nums,firstTarget,false);

            answer[0] = start;
            answer[1] = last;
        }else {
            answer[0] = left;
            answer[1] = left;
        }

        return answer;
    }


    private static int TwoSearch(int[] nums, int index,boolean flag){
//        //简单的一步步往前找，往后找，这已经击败了100%，但并不满足题目时间复杂度的要求
//        if (flag){
//            int temp = index;
//            while (nums[index]==nums[temp]){
//                if (temp==0) return temp;
//                temp -=1;
//            }
//            return temp+1;
//        }else {
//            int temp = index;
//            while (nums[index]==nums[temp]){
//                if (temp==nums.length-1) return temp;
//                temp +=1;
//            }
//            return temp-1;
//        }

        //二分法解决问题
        if (flag){
            if (index==0) return index;
            int left = 0;
            int right = index;
            while (1<right-left){
                int media = (left+right)/2;
                if (nums[media]<nums[index]) left = media+1;
                else if (right == media){
                    if (nums[right-1]==nums[index]) right -=1;
                    else return right;
                }else right = media;
            }
            if (nums[left]==nums[index]) return left;
            else return right;
        }else {
            if (index==nums.length-1) return index;
            int left = index ;
            int right = nums.length-1;
            while (1<right-left){
                int media = (left+right)/2;
                if (nums[media]>nums[index]) right = media-1;
                else  if (left==media){
                    if (nums[left+1]==nums[index]) left +=1;
                    else return left;
                }else {
                    left = media;
                }
            }
            if (nums[right]==nums[index]) return right;
            else return left;
        }
    }

    //领扣:
    //我的方法是先用二分法去找target值，再用二分法在target前找和target一样的最小值，
    // 那为什么不直接用二分法去找最小坐标和最大坐标呢----->官方的解答：方法2：二分查找
    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        int[] nums2 = {5,7,7,8,8,10};
        int target2 = 6;
        int[] nums3 = {1,2,3};
        int target3 = 2;
        int[] nums4 = {1,2,3,3,3,3,4,5,9};
        int target4 = 3;
        int[] answer = searchRange(nums4,target4);
        System.out.println(Arrays.toString(answer));
    }
}
