package leetcode;

/**
 * @author zg
 * @create 2019-12-11 11:57
 *
 * 最长回文子串[中等]
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 *
 *
 */
public class Test5 {
    public static  String  longestPalindrome(String s) {

        //处理1个字符串，必是回文
        if (s.length()==1) return s;

        //最长回文子串的长度
        int huiwenLongest =0;
        //最长回文子串
        String answer = "";

        for (int i = 0 ; i< s.length()-1 ; i++){
            int temp =0;
            int temp2 =0;
            temp= huiwen(s,i,i+1 , 0 );
            temp2 = huiwen(s,i-1,i+1, 1);

            if (temp >= temp2 && huiwenLongest<temp ){
                answer = s.substring(i-(temp-2)/2,i+1+(temp-2)/2+1);
                huiwenLongest = temp;
            }else if (temp < temp2 && huiwenLongest<temp2){
                answer = s.substring(i-(temp2-1)/2,i+(temp2-1)/2+1);
                huiwenLongest = temp2 ;
            }
        }
        return answer;
    }

    public static int huiwen (String s , int before, int after,int n ){
        if (before<0 || after>s.length()-1){
            return n;
        }else if (s.charAt(before)!=s.charAt(after)){
            return n;
        }else {
            n = huiwen(s,before-1,after+1,n+2);
        }
        return n;
    }



    public static void main(String[] args) {
        String s = "babad";
        String s2 = "cbbd" ;
        String s3 = "ccd" ;
        String s4 = "cccc";
        System.out.println(longestPalindrome(s4));
    }

}
