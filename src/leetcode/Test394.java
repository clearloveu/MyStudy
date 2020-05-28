package leetcode;

import java.util.Stack;

/**
 * @author zg
 * @create 2020-02-10 17:34
 *
 * 字符串解码[中等]
 *
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例:
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 *
 */
public class Test394 {
    private static String decodeString(String s) {


        //栈
        Stack<StringBuilder> string = new Stack<>();
        Stack<Integer> number = new Stack<>();
//        Stack<Character> kuohao = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        for(int index = 0;index<s.length();){
            //如果遇到数字，先把当前字符串加入字符栈中，初始化当前字符串，向右找到所有数字，加入数字栈
            if (s.charAt(index)-'0'>=0&&s.charAt(index)-'0'<=9){
                string.add(currentString);
                currentString = new StringBuilder();
                int startNumIndex = index;
                index +=1;
                while (s.charAt(index)!='['){
                    index +=1;
                }
                int currentNumber = Integer.parseInt(s.substring(startNumIndex,index));
                number.add(currentNumber);
                index +=1;//一定遇到"["，直接到下一位置
            }
//            else if(s.charAt(index)=='[') {  //如果遇到"["，直接continue
//                index +=1 ;
//            }
            else if (s.charAt(index)==']'){//如果遇到"]"，取出数字栈栈顶元素n，取出字符串栈顶元素m，当前字符串currentString=m+n倍的当前字符串
                int currentNumber = number.pop();
                StringBuilder prefixString = string.pop();
                StringBuilder temp = new StringBuilder(currentString);
                for(int i=0;i<currentNumber-1;i++){
                    currentString.append(temp);
                }
                prefixString.append(currentString);
                currentString = prefixString;
                index +=1;
            }
            else {   //如果遇到非数字非括号，直接扩展当前字符串currentString
                currentString.append(s.charAt(index));
                index +=1;
            }

        }
        return currentString.toString();
    }

    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        String s2 = "3[a2[c]]";
        System.out.println(decodeString(s2));
    }



}
