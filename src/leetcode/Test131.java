package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoguang
 * @create 2024-06-13 00:58
 *
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是
 * 回文串
 *  。返回 s 所有可能的分割方案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 *
 */
public class Test131 {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        inner2(s, 0, res, new ArrayList<>());
        return res;
    }

    private void inner2(String s, int index, List<List<String>> res, List<String> temp) {
        if(index > s.length() -1) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = index; i< s.length();i++) {
            if(inner(s, index, i)) {
                temp.add(s.substring(index, i+1));
                inner2(s, i+1, res, temp);
                temp.remove(temp.size() -1);
            }
        }
    }

    private boolean inner(String temp, int start, int end) {
        while(start<end) {
            if(temp.charAt(start) != temp.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        String a = "aab";
        System.out.println(new Test131().partition(a));
    }

}
