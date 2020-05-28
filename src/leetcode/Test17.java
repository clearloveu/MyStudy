package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zg
 * @create 2019-12-19 10:40
 *
 * 电话号码的字母组合[中等]
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *  示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 */
public class Test17 {
    private static Map<Integer,String> ziMuBiao = new HashMap<>();
    private static List<String> letterCombinations(String digits) {
        ziMuBiao.put(2,"abc");
        ziMuBiao.put(3,"def");
        ziMuBiao.put(4,"ghi");
        ziMuBiao.put(5,"jkl");
        ziMuBiao.put(6,"mno");
        ziMuBiao.put(7,"pqrs");
        ziMuBiao.put(8,"tuv");
        ziMuBiao.put(9,"wxyz");

        List<String> answers = new ArrayList<>();
        //特例，输入为""
        if (digits.length()==0) return answers;

        String answer = "";
        recursion(answers,digits,answer);
        return answers;
    }

    private static void recursion(List<String> answers,String digits,String answer){

        //递归基
        if (digits.length()==0){
            answers.add(answer);
            return;
        }
        //递归体
        //char--->int时，0代表48，1代表49，减去0对应的char转int值
        Integer firstInt = (int)digits.charAt(0)-48;
        String ziMu = ziMuBiao.get(firstInt);
        for (int i = 0;i<ziMu.length();i++){
            String answerTemp = answer+ziMu.charAt(i);
            recursion(answers,digits.substring(1),answerTemp);
        }
    }


    public static void main(String[] args) {
        String digits = "23";
        List<String> list = letterCombinations(digits);
        for (String temp: list) {
            System.out.print(temp+" ");
        }
    }


}
