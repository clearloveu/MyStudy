package leetcode;

import java.util.*;

/**
 * @author zg
 * @create 2019-12-18 21:42
 *
 * 三数之和[中等]
 *
 *  给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 */
public class Test15 {
    //复杂度过高，看领扣解答
    static int[] nums2;
    private static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answers = new ArrayList<>();
        //先数组排序
        Arrays.sort(nums);
        nums2 = nums;
        Set<Integer> set = new HashSet<>();
        //遍历每个数字，以该数字为递归的开始
        for (int i = 0;i<nums.length;i++){
            //set检测该数字是否已经出现过，如果该数字已经出现过，则跳过递归
            if (!set.contains(nums[i])){
                recursion(i,answers);
                set.add(nums[i]);
            }
        }

        //之前没有考虑到先将nums排序后，再加上递归时去掉之前已经递归过的数字，否则最后的结果会有重复的三元组，按照以下思路去重
        //去重
        //利用基数排序的思想,逐位比较====》代码复杂，但时间复杂度低
        //利用插入排序的思想
        //先比较第一位，把第一位最小的放在一起，再比较第2位，第3位，这样类似于字典序一样的排序，最后两两对比，去重

        return answers;
    }

    private static void recursion(int index,List<List<Integer>> answers){
        //另外2个数字的和
        int target = 0-nums2[index];
        //类似于Test1的方法，用map去找和为target的2个数字，这样时间复杂度低
        Map<Integer,Integer> map = new HashMap<>();
        //[0,0,0,0],保证后2个数字不重复
        Set<String> set = new HashSet<>();
        //从index+1开始找这2个数，即剪枝掉之前已经遍历过的元素，因为包含前面的元素的三数之和为0的三元组一定已经找到了，若再从
        //头开始找的话，结果会重复，这样的前提是该数组已经排序过了，当然[0,0,0,0]这样的任然会重复，利用Set去这种重
        //该思想参考Test46无重复的全排序的思想
        for (int i=index+1;i<nums2.length;i++){
            int otherInt = target - nums2[i];
            if (map.get(otherInt)==null){
                map.put(nums2[i],i);
            }else {
                String temp = ""+nums2[map.get(otherInt)]+nums2[i];
                if (!set.contains(temp)){
                    set.add(temp);
                    List<Integer> answer = new ArrayList<>();

//                int[] temp = {nums2[index],nums2[i],nums2[map.get(otherInt)]};
//                Arrays.sort(temp);
//                answer.add(temp[0]);
//                answer.add(temp[1]);
//                answer.add(temp[2]);
                    answer.add(nums2[index]);
                    answer.add(nums2[i]);
                    answer.add(nums2[map.get(otherInt)]);
                    answers.add(answer);
                }


            }
        }
    }


    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> answers = threeSum(nums);
        System.out.println(answers.size());
        for (int i=0;i<answers.size();i++){
            for (int j=0;j<answers.get(i).size();j++){
                System.out.print(answers.get(i).get(j));
            }
            System.out.println();
        }
    }
}
