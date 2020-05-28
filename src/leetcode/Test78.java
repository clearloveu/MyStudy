package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zg
 * @create 2019-12-25 17:39
 *
 * 子集[中等]                [未完成]
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 */
public class Test78 {
    private static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>>  answers = new ArrayList<>();
        if (nums.length==0) return answers;
        List<Integer> answer = new ArrayList<>();
        recursion(answers,nums,0,answer);

        return answers;
    }


    private static void  recursion(List<List<Integer>>  answers,int[] nums,int index,List<Integer> answer){
        //递归基
        if(index==nums.length) {
            answers.add(answer);
            return;
        }

        //每一个数字都有选中和不选中2个选择
        //先选中index的字符
        answer.add(nums[index]);
        recursion(answers,nums,index+1,answer);
        answer.remove(answer.size()-1);
        //不选中index的字符
        recursion(answers,nums,index+1,answer);

    }


    //领扣题解的代码：https://leetcode-cn.com/problems/subsets/solution/hui-su-suan-fa-by-powcai-5/
    //递归(回溯算法)，
    // 这题是Test39的升级。
    //在Test39组合总和中，每次去尝试一种组合时，如果不对，则回溯到上一个选择，选择另一个。
    //本题，有所不同的是，每次去尝试，就把该次尝试的加入答案中，因为只要不到达数字最右端，这样尝试的组合都是子集。
    //难理解的点：
    //1，怎么保证这样会把所有的子集都能加进去：因为上面的递归算法本质上是一个个遍历去尝试的，它会尝试所有的情况，
    // 因此只要每次递归时都加入到答案中，就能保证。
    //2，怎么保证不出现重复的答案：题目中说它提供的数组是一组不含重复元素的整数数组 ，所以只要保证每次递归时，
    // 都不会去遍历已经遍历的元素即上面代码中递归时j+1，即可保证不重复。
    //
    //这里的组合无关乎整数大小，所以不需要和Test39一样，要先进行排序才能保证无重复。

//    private static List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> res = new ArrayList<>();
//        backtrack(0, nums, res, new ArrayList<Integer>());
//        return res;
//
//    }
//
//    private static void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
//        res.add(new ArrayList<>(tmp));
//        for (int j = i; j < nums.length; j++) {
//            tmp.add(nums[j]);
//            backtrack(j + 1, nums, res, tmp);
//            tmp.remove(tmp.size() - 1);
//        }
//    }


    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(subsets(nums).toString());
    }
}
