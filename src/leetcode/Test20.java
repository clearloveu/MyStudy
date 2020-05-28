package leetcode;

import java.util.Stack;

/**
 * @author zg
 * @create 2019-12-09 21:34
 *
 * 有效的括号[简单]
 *
 *给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 *
 * 1 左括号必须用相同类型的右括号闭合。
 * 2 左括号必须以正确的顺序闭合。
 *
 * 例：
 * 输入: "{[]}"
 * 输出: true
 */
public class Test20 {
    public static boolean isValid(String s) {

        Stack <Character> stack = new Stack<>();

        if (s.charAt(0)==')' || s.charAt(0)== ']' || s.charAt(0) =='}' ) return false;

        for (int i =0 ; i<s.length();i++){
            Character var = s.charAt(i);


            if(var=='(' || var== '[' || var =='{' || stack.empty()){
                stack.push(var);

            }else if (var==')'){
                if (stack.peek()=='(') stack.pop();else stack.push(var);
            }else if (var ==']'){
                if (stack.peek()=='[') stack.pop();else stack.push(var);
            }else{
                if (stack.peek()=='{') stack.pop();else stack.push(var);
            }
        }

        if (stack.empty()){
            return true;
        }else {
            return false;
        }

    }

    public static void main(String[] args) {
        String s = "{[]}";
        String s2 = "([)]" ;
        boolean isvalid = isValid(s2);
        System.out.println(isvalid);
    }
}
