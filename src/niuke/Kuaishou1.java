package niuke;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * 快手笔试4.12
 *
 *
 */
public class Kuaishou1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String string = in.nextLine();
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i)=='(' ) {
                stack.push('(');
            }else if (string.charAt(i)==')'){
                if (!stack.isEmpty()&&stack.peek()=='('){
                    count++;
                    stack.pop();
                }else stack.push(')');
            }
        }
        int countZuo = 0;
        int countYou = 0;
        while (!stack.isEmpty()){
            char temp = stack.pop();
            if (temp=='(') countZuo++;
            if (temp==')') countYou++;
        }
        System.out.println(count+" "+countZuo+" "+countYou);
    }
}
