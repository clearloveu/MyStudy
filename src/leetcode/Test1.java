package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zg
 * @create 2019-12-05 21:38
 *
 * 两数之和[简单]
 *
 * 说明：
 *  给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 *
 *
 */
public class Test1 {
    public int[] twoSum(int[] nums, int target) {

        Map<Integer,Integer> maps = new HashMap<>() ;
        for(int i = 0 ; i<nums.length;i++){
            int other = target-nums[i];

            if (maps.get(other)==null){
                maps.put(nums[i],i);
            }
            else {
                int index = maps.get(other);
                return new int[]{i , index};
            }
        }

        return null;
    }


    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;

        Test1 test1= new Test1();
        //注意数组到字符串的转换（有道云---java中有详述）
        System.out.println(Arrays.toString(test1.twoSum(nums , target)));
    }

}
