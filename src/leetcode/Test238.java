package leetcode;

/**
 * @author zg
 * @create 2020-01-11 16:07
 *
 * 除自身以外数组的乘积[中等]
 *
 * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 * 示例:
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 */
public class Test238 {
    // 乘积 = 当前数左边的乘积 * 当前数右边的乘积
    // 用双指针标记每个位置的左积(left)和右积(right)，这样在一次遍历中就可以计算出结果。
    private static int[] productExceptSelf(int[] nums) {
        //初始化
        int[] out = new int[nums.length];
        for(int i = 0;i<nums.length;i++){
            out[i] = 1;
        }
        //左积
        int left = 1;
        //右积
        int right = 1;
        for(int i =0;i<nums.length;i++){
            left = left*nums[i];
            right = right*nums[nums.length-1-i];
            if(i!=nums.length-1) {
                out[i+1] *= left;
                out[nums.length-1-i-1] *=right;
            }
        }
        return out;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int[] answer = productExceptSelf(nums);
        for (int i=0;i<nums.length;i++){
            System.out.print(answer[i]+" ");
        }
    }
}
