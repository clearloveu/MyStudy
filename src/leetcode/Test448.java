package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zg
 * @create 2020-02-11 17:16
 *
 * 找到所有数组中消失的数字[简单]
 *
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 *
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 *
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 *
 * 示例:
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * 输出:
 * [5,6]
 */
public class Test448 {
    private static List<Integer> findDisappearedNumbers(int[] nums) {
        // //使用额外空间
        // int[] nums2 = new int[nums.length];
        // for(int i=0;i<nums.length;i++){
        //     nums2[nums[i]-1]=1;
        // }
        // List<Integer> answers = new LinkedList<>();
        // for(int i=0;i<nums.length;i++){
        //     if(nums2[i]==0) answers.add(i+1);
        // }
        // return answers;


        //不使用额外空间
        //1应该呆在索引0的位置，2应该呆在索引1的位置
        for(int i=0;i<nums.length;i++){
            //找到nums[i]适合的位置
            int temp = nums[i];
            while(true){
                if(temp==i+1) break;//如果该索引找到了值，则break
                if (temp==0) break;//如果是无效数字
                if(temp==nums[temp-1]){//如果2个要交换的数字是相同的
                    nums[i]=0; //先把该索引的位置置0，代表暂时没找到该索引的值
                    break;
                }
                //交换
                temp = nums[temp-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            }

        }
        List<Integer> answers = new LinkedList<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0) answers.add(i+1);
        }
        return answers;
    }

    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(findDisappearedNumbers(nums));

    }
}
