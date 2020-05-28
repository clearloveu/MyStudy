package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author zg
 * @create 2019-12-13 11:05
 *
 * 组合总和[中等]
 *
 *  给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 *  candidates 中的数字可以无限制重复被选取。
 *
 *  说明：1，所有数字（包括 target）都是正整数。2，解集不能包含重复的组合。
 *
 *  示例 :
 *  输入: candidates = [2,3,5], target = 8,
 *  所求解集为:[[2,2,2,2],[2,3,3],[3,5]]
 *
 */
public class Test39 {
    // 2020/3/28
    List<List<Integer>> res2 ;
    private List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res2 = new ArrayList<>();

        Arrays.sort(candidates);
        List<Integer> list = new ArrayList<>();
        rersion2(candidates,0,target,list);

        return res2;
    }

    private void rersion2(int[] candidates, int index, int target, List<Integer> list){
        //递归基
        if(target==0) {
            List<Integer> temp = new ArrayList<>(list);
            res2.add(temp);
            return;
        }else if(target<0) return;

        for(int i=index;i<candidates.length;i++){
            list.add(candidates[i]);
            rersion2(candidates,i,target-candidates[i],list);
            list.remove(list.size()-1);
        }

    }

    private List<List<Integer>> res = new ArrayList<>();
    private int[] candidates;
    private int len;

    private void findCombinationSum(int residue, int start, Stack<Integer> pre) {
        if (residue == 0) {
            // Java 中可变对象是引用传递，因此需要将当前 path 里的值拷贝出来
            res.add(new ArrayList<>(pre));
            return;
        }
        // 优化添加的代码2：在循环的时候做判断，尽量避免系统栈的深度
        // residue - candidates[i] 表示下一轮的剩余，如果下一轮的剩余都小于 0 ，就没有必要进行后面的循环了
        // 这一点基于原始数组是排序数组的前提，因为如果计算后面的剩余，只会越来越小
        for (int i = start; i < len && residue - candidates[i] >= 0; i++) {
            pre.add(candidates[i]);
            // 【关键】因为元素可以重复使用，这里递归传递下去的是 i 而不是 i + 1
            findCombinationSum(residue - candidates[i], i, pre);

            //如果没有这句话，因为java函数的参数是非基本类型的话，则是引用传递，最后操作的结果都在一个栈中，即第一个结果集确实是正确的，
            //第2个结果集中有第一个结果集的数据，虽然程序逻辑上是找到了第二个结果的，但是就结果集看，第二个结果是错的
            //如果每次在回溯的时候，都有个pop操作，就相当于利用pop() 来恢复现场，恢复到原来的栈
            //C++和python没有pop这个操作，因为C++ 这里传递变量到下一层的时候，是创建了一个新的对象，即下一层的结果不影响上一层的结果，
            // 因此，C++ 的代码不用 pop()
            //可以每次递归的时候，新建一个对象传进去，这样java也可以避免了调用pop()函数，可以类似C++和python
            pre.pop();
        }
    }

    private List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        if (len == 0) {
            return res;
        }
        // 优化添加的代码1：先对数组排序，可以提前终止判断
        Arrays.sort(candidates);
        this.len = len;
        this.candidates = candidates;
        findCombinationSum(target, 0, new Stack<>());
        return res;
    }




    public static void main(String[] args) {
        int[] candidates = {2,3,6,7} ;
        int target = 7;
        List<List<Integer>> answers = new Test39().combinationSum2(candidates,target);

        System.out.println(answers.toString());

    }


}
