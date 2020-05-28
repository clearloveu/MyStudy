package leetcode;

/**
 * @author zg
 * @create 2019-12-16 20:21
 *
 * 跳跃游戏[中等]
 *
 *  给定一个非负整数数组，你最初位于数组的第一个位置。数组中的每个元素代表你在该位置可以跳跃的最大长度。判断你是否能够到达最后一个位置。
 *
 *  示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 *
 *
 * 示例 2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 *
 *
 */
public class Test55 {
    private static boolean canJump(int[] nums) {
        boolean flag = false;
        //特例,数组长度是1，自然成立
        if (nums.length==1) return true;
        //第二个参数是代表要到达的位置，初始值是数组的最后一个元素的秩
        flag = recursion(nums,nums.length-1);
        return flag;
    }

    private static boolean recursion(int[] nums, int target){
        //递归基，如果需要到达的数组的位置是第一个元素的话，自然成立
        if (target==0) return true;

        //递归体
        //初始步长是1，设需要到达的位置是a，则我们从a的前面一个元素开始向前循环，步长最开始是1
        int step = 1;
        for (int i = target-1;i>=0;i--){
            //如果这个数组元素的值大于等于到达指定位置的所需要的步长，则只要能先到达这个元素的位置，则就一定可以到达指定位置
            //故递归，传入第2个参数是该数组元素的秩
            if (nums[i]>=step) return recursion(nums,i);
            //如果小于，则该元素到不了指定位置，步长+1，测试再前一个数组元素
            step += 1;
        }
        //如果数组中没有元素能到达指定位置的元素，则自然返回false
        return false;
    }


    public static void main(String[] args) {
        int[] nums = {3,2,1,0,4};
        int[] nums2 = {2,3,1,1,4};
        int[] nums3 = {2,0,0};
        System.out.println(canJump(nums3));
    }

}
