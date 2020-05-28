package leetcode;

/**
 * @author zg
 * @create 2020-02-17 13:12
 *
 * 回文子串[中等]
 *
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * 示例 1:
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 *
 * 示例 2:
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 *
 * 注意:
 * 	输入的字符串长度不会超过1000。
 *
 */
public class Test647 {
    private int answer ;
    // 暴力法
    private int countSubstrings(String s) {
        answer = 0;
        for(int i=0;i<s.length();i++){
            countHuiWen(s,i);

        }
        return answer;
    }


    private void countHuiWen(String s, int index){
        // 以s.charAt(index)为对称的回文子串
        int left = 0;
        while((index-left>=0)&&(index+left<s.length()) ){
            if(s.charAt(index+left)==s.charAt(index-left)) {
                answer+=1;
                left +=1;
            }
            else break;
        }

        // 以s.charAt(index)和s.charAt(index+1)为对称的回文子串
        left =0;
        while((index-left>=0)&&(index+1+left<s.length()) ){
            if(s.charAt(index+1+left)==s.charAt(index-left)) {
                answer+=1;
                left +=1;
            }
            else break;
        }
    }
}
