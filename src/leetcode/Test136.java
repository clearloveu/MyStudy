package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zg
 * @create 2019-12-25 16:11
 *
 * 只出现一次的数字[简单]
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 *
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 */
public class Test136 {
    //使用额外空间
    private static int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i =0;i<nums.length;i++){
            if (set.contains(nums[i])) set.remove(nums[i]);
            else set.add(nums[i]);
        }
        int answer = Integer.MAX_VALUE;
        for (Integer temp:set){
            answer = temp;
        }
        return answer;
    }

    //不使用额外空间：位操作
    private static int singleNumber2(int[] nums) {
        int answer = 0;
        for (int i=0;i<nums.length;i++){
            answer ^= nums[i];
        }
        return answer;
    }


    public static void main(String[] args) {
        int[] nums = {4,1,2,1,2};
        System.out.println(singleNumber2(nums));
    }
}
