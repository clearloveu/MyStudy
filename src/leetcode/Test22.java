package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zg
 * @create 2019-12-09 21:57
 *
 * 括号生成[中等]            [未完成]
 *
 *  给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 */
public class Test22 {
    private static List<String> generateParenthesis(int n) {

        List<String > list = new ArrayList<>();

        //list：最终要返回的有效括号组合的数组
        //""：某一个有效组合，初始时为空
        //0：左括号的数量，0：右括号的数量，n：n对括号
        backTrack(list,"",0,0,n);

        return list;
    }


    //引用传递的重要，不一定要通过返回值进行赋值
    private static void backTrack(List<String> list, String abc, int left, int right, int n){
        if (abc.length()==2*n){
            list.add(abc);
        }

        //贪心，先优先"("
        if (left<n){
            backTrack(list,abc+"(",left+1,right,n);
        }

        //如果最终每个位置左括号的数量大于等于右括号的数量，则一定是有效的括号组合
        //所以，只要左括号的数量大于右括号的数量，则可以加")"
        if (left>right){
            backTrack(list,abc+")",left,right+1,n);
        }
    }


    public static void main(String[] args) {
        int n =3 ;
        List<String> list = generateParenthesis(n);

        int an = list.size();
        System.out.println(an);


    }

}
