package leetcode;

/**
 * @author zg
 * @create 2019-12-09 22:02
 *
 * 最长有效括号[困难]              [未完成]
 *
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 *
 *  例：
 *  输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 */
public class Test32 {


    private static int longestValidParentheses(String s) {

        if (s.length()<=1) return 0;

        //暴力法,判断字符串中的每一个子串是否为有效括号组合
        int max_vaild_length = s.length()%2==0?s.length():s.length()-1;
        for (int i = max_vaild_length ; i>=2;i=i-2){
            for (int j = 0 ; j<=s.length()-i;j++){
                String sub_s = s.substring(j,j+i);
                if (isVaild(sub_s)){
                    return i;
                }
            }
        }
        return 0;
    }


    private static  boolean isVaild(String s){
        int flag = 0 ;
        for (int i = 0 ; i<s.length();i++){
            if (s.charAt(i)=='(') flag+=1;
            else {
                flag-=1;
                if (flag<0) return false;
            }

        }
        return flag==0;
    }


    //官方解答
    //方法 3：栈
    //算法
    //与找到每个可能的子字符串后再判断它的有效性不同，我们可以用栈在遍历给定字符串的过程中去判断到目前为止扫描的子字符串的有效性，
    // 同时能的都最长有效字符串的长度。我们首先将 −1 放入栈顶。
    //
    //对于遇到的每个 ‘(’ ，我们将它的下标放入栈中。
    //对于遇到的每个 ‘)’ ，我们弹出栈顶的元素并将当前元素的下标与弹出元素下标作差，得出当前有效括号字符串的长度。
    // 通过这种方法，我们继续计算有效子字符串的长度，并最终返回最长有效子字符串的长度。


    //方法4：
    //在这种方法中，我们利用两个计数器 left 和 right 。首先，我们从左到右遍历字符串，
    // 对于遇到的每个 ‘(’，我们增加 left计算器，对于遇到的每个 ‘)’ ，
    // 我们增加 right 计数器。每当 left 计数器与 right 计数器相等时，
    // 我们计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串。如果 right 计数器比 left 计数器大时，
    // 我们将 left 和 right 计数器同时变回 0。
    //接下来，我们从右到左做一遍类似的工作。
    //    public int longestValidParentheses(String s) {
    //        int left = 0, right = 0, maxlength = 0;
    //        for (int i = 0; i < s.length(); i++) {
    //            if (s.charAt(i) == '(') {
    //                left++;
    //            } else {
    //                right++;
    //            }
    //            if (left == right) {
    //                maxlength = Math.max(maxlength, 2 * right);
    //            } else if (right >= left) {
    //                left = right = 0;
    //            }
    //        }
    //        left = right = 0;
    //        for (int i = s.length() - 1; i >= 0; i--) {
    //            if (s.charAt(i) == '(') {
    //                left++;
    //            } else {
    //                right++;
    //            }
    //            if (left == right) {
    //                maxlength = Math.max(maxlength, 2 * left);
    //            } else if (left >= right) {
    //                left = right = 0;
    //            }
    //        }
    //        return maxlength;
    //    }



    public static void main(String[] args) {
        String  s = ")()())";
        String  s2 = ")(";
        System.out.println(longestValidParentheses(s2));
    }
}
