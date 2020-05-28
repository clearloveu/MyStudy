package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zg
 * @create 2019-12-18 19:24
 *
 * 全排列[中等]
 *
 *  给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 *  示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 */
public class Test46 {
    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answers = new ArrayList<>();
        List<Integer> answer = new LinkedList<>();
        //特例
        if (nums.length==0) {
            answers.add(answer);
            return answers;
        }
        //把数组变成链表形式的数据结构，因为递归过程中为了回溯时恢复原来的状态，会一直增加元素或者删除元素，而且数组不太好增删，复杂度高
        List<Integer> temps = new LinkedList<>();
        for (int num : nums) {
            temps.add(num);
        }

        recursion(answers,answer,temps);
        //引用传递，在递归过程中增加answers元素
        return answers;
    }

    private static void recursion (List<List<Integer>> answers, List<Integer> answer ,List<Integer> temps){
        //递归基
        if (temps.size()==0) {
            //重点，必须要新建一个对象，这个对象不能在递归过程中改变，如果直接把answer添加进去的话，answer后面会变
            List<Integer> lastAnswer = new ArrayList<>(answer);
            answers.add(lastAnswer);
            return;
        }
        //递归体
        //首先想到的是数学的排列组合, N个元素有多少种排列方式呢, N*(N-1)*(N-2)..*(1),
        // 第一个位置有N种选择, 第二个位置还有N-1种元素可选, 第三个位置还有N-2种元素可选,
        //在第一个次递归时选择数组中的一个元素作为answer的第一个元素，把这个元素从数组中剔除出去，就剩下N-1个元素进行全排序，剩下的继续递归
        int temps_len = temps.size();
        for (int i = 0; i<temps_len;i++){
            //这种递归操作参考有道云笔记中领扣-细节-递归问题中的引用传递的解决办法（难点）
            int temp = temps.get(i);
            answer.add(temp);
            temps.remove(i);
            recursion(answers,answer,temps);
            //回溯，恢复到递归前的状态
            answer.remove(answer.size()-1);
            temps.add(i,temp);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> answers = permute(nums);
        System.out.println(answers.size());
        for (int i=0;i<answers.size();i++){
            for (int j=0;j<answers.get(i).size();j++){
                System.out.print(answers.get(i).get(j));
            }
            System.out.println();
        }
    }
}
