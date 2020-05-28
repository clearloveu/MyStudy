package leetcode.jianZhiOffer;

import java.util.Arrays;

/**
 * @author zg
 * @create 2020-03-12 20:36
 *
 * 数组中数字出现的次数[中等]
 *
 */
public class Question56_1 {
    // 参考剑指offer
    private int[] singleNumbers(int[] nums) {
        int sum = 0;
        //思路重点是异或
        for(int i=0;i<nums.length;i++){
            sum^=nums[i];
        }
        int index  = 1; //代表sum的二进制第几位不是0
        while(true){
            if((sum&index)!=0) break;
            index = index << 1;
        }
        //分成2个子数组（仿照快排思想）
        int left = 0;
        int right = nums.length-1;
        int pivot = nums[left];
        while(left<right){
//            if((nums[left]&index)==0) left++;
//            else{
//                int temp = nums[right];
//                nums[right] = nums[left];
//                nums[left] = temp;
//                right --;
//            }
            while(left<right&&(nums[right]&index)!=0) right--;
            nums[left] = nums[right];
            while (left<right&&(nums[left]&index)==0) left++;
            nums[right] = nums[left];

        }
        nums[left] = pivot;
        // pivot不确定是属于哪一个子数组
        int pivot2 = (nums[left]&index)==0?left:left-1;

        int[] res = new int[2];
        for(int i=0;i<=pivot2;i++){
            res[0]^=nums[i];
        }
        for(int i=pivot2+1;i<nums.length;i++){
            res[1]^=nums[i];
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Question56_1().singleNumbers(new int[]{3, 2, 1, 3})));
    }

}
