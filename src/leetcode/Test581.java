package leetcode;

import java.util.Arrays;

/**
 * @author zg
 * @create 2020-02-14 16:36
 *
 * 最短无序连续子数组[简单]
 *
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 你找到的子数组应是最短的，请输出它的长度。
 *
 * 示例 1:
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 *
 * 说明 :
 * 	输入的数组长度范围在 [1, 10,000]。
 * 	输入的数组可能包含重复元素 ，所以升序的意思是<=。
 *
 */
public class Test581 {
    //领扣：使用栈：O(n)：**算法背后的思想是无序子数组中最小元素的正确位置可以决定左边界，最大元素的正确位置可以决定右边界。**
    private int findUnsortedSubarray(int[] nums) {

        //直接先排序，然后在一个个对比：O(nlog(n))
        int[] temp = nums.clone();  //克隆
        Arrays.sort(temp);   //默认升序
        int start = 0;
        int end = -1;  //为了保证如果nums是升序的，则start和end不会被赋新值，且需要返回0，所以start+end = -1；
        for(int i=0;i<nums.length;i++){
            if(temp[i]!=nums[i]) {
                start=i;
                break;
            }
        }
        for(int i=nums.length-1;i>=0;i--){
            if(temp[i]!=nums[i]) {
                end=i;
                break;
            }
        }
        return end-start+1;
    }


    public static void main(String[] args) {
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        System.out.println(new Test581().findUnsortedSubarray(nums));
    }
}
