package leetcode.jianZhiOffer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zg
 * @create 2020-03-12 17:21
 *
 * 最长不含重复字符的子字符串[中等]
 *
 */
public class Question48 {
    private int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        Set<Character> set = new HashSet<>();
        int answer = 0;
        while (left < s.length()) {
            //right移动
            Character reNum = '0';
            while (right < s.length()) {
                if (set.contains(s.charAt(right))) {
                    reNum = s.charAt(right);
                    answer = Math.max(answer, right - left);
                    break;
                } else {
                    set.add(s.charAt(right));
                    right++;
                    answer = Math.max(answer, right - left);
                }
            }
            //left移动
            while (left < s.length()) {
                set.remove(s.charAt(left));
                if (s.charAt(left) == reNum) {
                    left++;
                    answer = Math.max(answer, right - left);
                    break;
                } else left++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Question48().lengthOfLongestSubstring("a"));
    }
}
