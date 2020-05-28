package leetcode;

/**
 * @author zg
 * @create 2019-12-11 16:40
 *
 * 正则表达式匹配[困难]
 *
 *  给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *  '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 *
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 *
 *
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 *输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 */
public class Test10 {

    public static boolean isMatch(String s, String p) {
        boolean flag = false;
        flag=isVaild(s,p);
        return flag;
    }

    private static boolean isVaild(String s, String p){
        //递归基
        if (s.length()==0 ){
            //如果p，s长度都为0，则返回true
            if (p.length()==0) return true;
            else if (p.length()==1) return false;
            else if (p.charAt(1)=='*') return  isVaild(s,p.substring(2));//s为空，p的第2个字符为*的情况，如s="",p="a*"
            else return false;//s为空，p不为空，且第2个字符不是*
        }else {
            //如果s不为空，p为空，则返回false
            if (p.length()==0) return false;
        }
        //下面是s不为空，p也不为空的情况

        //处理长度为1的p，因为下面需要判断p的第2个字符是否为*
        if (p.length()==1) {
            if (s.length()==1) {
                if (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0)) return true;
                else return false;
            }else return false;
        }
        //递归
        //p的第2个字符不是*，情况简单
        if (p.charAt(1)!='*'){
            //中间某步出现不匹配，直接返回false
            if (p.charAt(0)!='.'&& p.charAt(0)!=s.charAt(0)) return false;
            if (p.charAt(0)=='.' || p.charAt(0) == s.charAt(0)) return  isVaild(s.substring(1),p.substring(1));
        }
        //p的第2个字符是*的情况

        //第一个字符不相等
        if (p.charAt(0)!='.' && p.charAt(0)!=s.charAt(0)) return isVaild(s,p.substring(2));
        //第一个字符相等，匹配几个的问题

        //如果p没有第三个字符
        if (p.length()<3){
            //如果s剩下的字符都是p的第一个字符，则匹配成功,否则失败
            if (findSameChar(s,p.charAt(0))==s.length()-1) return true;
            else return false;
        }
        //p有第三个字符，则找到p的第1个字符和s开头子串匹配的最后一个字符位置
        int lastSameCharIndex = findSameChar(s,p.charAt(0));
        // "p.charAt(0) *" 和s开头的每一种情况匹配，如果在某一种情况下能匹配成功，则在此情况下返回true，否则遍历其他情况
        // lastSameCharIndex+1 代表第一个不匹配的字符的位置，"a".charAt(1)不会报错，可能内部有通配符
        for (int i = 0 ; i<=lastSameCharIndex+1;i++){
            if(isVaild(s.substring(i), p.substring(2))) return true;
        }

        return false;
    }

    //找到s开头子串中最后一个和p一样的字符位置
    public  static  int findSameChar (String s , char p){
        //是*，直接返回最后一个字符位置
        if (p=='.') return s.length()-1;
        for (int i = 1 ; i<s.length() ; i++){
            if(s.charAt(i)!=p) return i-1;
        }
        return s.length()-1;
    }

    //java
    //递归
    class Solution {
        public boolean isMatch(String text, String pattern) {
            if (pattern.isEmpty()) return text.isEmpty();
            boolean first_match = (!text.isEmpty() &&
                    (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

            if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
                return (isMatch(text, pattern.substring(2)) ||
                        (first_match && isMatch(text.substring(1), pattern)));
            } else {
                return first_match && isMatch(text.substring(1), pattern.substring(1));
            }
        }
    }
    //动态规划
    class Solution2 {
        public boolean isMatch(String text, String pattern) {
            boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
            dp[text.length()][pattern.length()] = true;

            for (int i = text.length(); i >= 0; i--){
                for (int j = pattern.length() - 1; j >= 0; j--){
                    boolean first_match = (i < text.length() &&
                            (pattern.charAt(j) == text.charAt(i) ||
                                    pattern.charAt(j) == '.'));
                    if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                        dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                    } else {
                        dp[i][j] = first_match && dp[i+1][j+1];
                    }
                }
            }
            return dp[0][0];
        }
    }


    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b";
        String s1 = "ab";
        String p1 = ".*";
        String s2 = "mississippi";
        String p2 = "mis*is*ip*.";
        String s3 = "ab";
        String p3 = ".*c";
        String s4 = "a";
        String p4 = "ab*";
        System.out.println(isMatch(s4,p4));
    }

}
