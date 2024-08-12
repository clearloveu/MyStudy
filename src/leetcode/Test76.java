package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。



 注意：

 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 如果 s 中存在这样的子串，我们保证它是唯一的答案。


 示例 1：

 输入：s = "ADOBECODEBANC", t = "ABC"
 输出："BANC"
 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 示例 2：

 输入：s = "a", t = "a"
 输出："a"
 解释：整个字符串 s 是最小覆盖子串。
 示例 3:

 输入: s = "a", t = "aa"
 输出: ""
 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 因此没有符合条件的子字符串，返回空字符串。
 *
 *
 */
public class Test76 {
    public static String minWindow(String s, String t) {
        int left = 0;
        int right = 0;
        String res = "";
        Map<Character, Integer> current = new HashMap<>();
        Map<Character, Integer> map = new HashMap<>();
        int match = 0;
        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) +1);
        }
        while(right < s.length()) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                current.put(s.charAt(right), current.getOrDefault(s.charAt(right), 0) +1);
                if (current.get(s.charAt(right)).equals(map.get(s.charAt(right)))) {
                    match++;
                }
            }
            while (match == map.size()) {
                if (map.containsKey(s.charAt(left))) {
                    if (current.get(s.charAt(left)).equals(map.get(s.charAt(left)))) {
                        String temp = s.substring(left, right + 1);
                        if (res.equals("")) {
                            res = temp;
                        } else {
                            res = res.length() > temp.length() ? temp : res;
                        }
                        match--;
                    }
                    current.put(s.charAt(left), current.get(s.charAt(left)) - 1);
                }
                left++;
            }
            right++;
        }
        return res;
    }



    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String s2 = "ABC" ;
        System.out.println(minWindow(s, s2));
    }

}
