package leetcode;

/**
 * @author zg
 * @create 2020-01-10 16:47
 *
 * 多数元素[简单]
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 *
 */
public class Test169 {
    private static int majorityElement(int[] nums) {
        if (nums.length==1) return nums[0];
        //寻找有可能成为众数的数
        int count = 1;
        int zongShu = nums[0];
        for (int i = 1; i<nums.length;i++){
            if (nums[i]!=zongShu) count -=1;
            else count+=1;
            if (count==0) {
                count = 1;
                zongShu = nums[i];
            }
        }
        //验证这个zongshu是不是众数
        count = 0;
        for (int i = 0;i<nums.length;i++){
            if (zongShu!=nums[i]){
                count +=1;
            }
        }
        int standard = nums.length/2;
        if (count>=standard) return zongShu;
        else return -1;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,3};
        System.out.println(majorityElement(nums));
    }
}
