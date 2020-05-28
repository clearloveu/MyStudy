package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zg
 * @create 2020-02-19 10:43
 *
 * 找到字符串中所有字母异位词[中等]                   [未完成]
 *
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 * 	字母异位词指字母相同，但排列不同的字符串。
 * 	不考虑答案输出的顺序。
 *
 * 示例 1:
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * 输出:
 * [0, 6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *  示例 2:
 * 输入:
 * s: "abab" p: "ab"
 * 输出:
 * [0, 1, 2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 *
 */
public class Test438 {
    //参考：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/solution/hua-dong-chuang-kou-tong-yong-si-xiang-jie-jue-zi-/
    private List<Integer> findAnagrams(String s, String p) {
        // 滑动窗口,双指针
        List<Integer> answers = new ArrayList<>();
        Map<Character,Integer> needs = new HashMap<>();
        Map<Character,Integer> windows = new HashMap<>();
        for(char temp:p.toCharArray()){
            needs.put(temp,needs.getOrDefault(temp,0)+1);

        }
        // 如何确定都找到了，此时right-left = p.length()
        int left = 0;
        int right = 0;
        int match = 0;
        while(right<s.length()){
            char temp = s.charAt(right);
            if(needs.get(temp)!=null){
                windows.put(temp,windows.getOrDefault(temp,0)+1);
                if(needs.get(temp).equals(windows.get(temp))) match+=1;
            }
            while(match==needs.size()){
                if(right-left+1==p.length()) answers.add(left);
                char leftChar = s.charAt(left);
                if(windows.get(leftChar)!=null){
                    windows.put(leftChar,windows.get(leftChar)-1);
                    if(needs.get(leftChar)>windows.get(leftChar)) match-=1;
                }
                left +=1;
            }
            right +=1;
        }
        return answers;
    }

    public static void main(String[] args) {
        new Test438().findAnagrams("cbaebabacd","abc");
    }
}
