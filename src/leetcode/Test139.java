package leetcode;

import java.util.*;

/**
 * @author zg
 * @create 2019-12-30 14:41
 *
 * 单词拆分[中等]                   [超出时间限制]：没有加备忘录进行记忆化回溯
 *
 *
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 	拆分时可以重复使用字典中的单词。
 * 	你可以假设字典中没有重复的单词。
 *
 *
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 *
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 *
 *
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 */
public class Test139 {
    Map<Integer, Boolean> dict = new HashMap<>();
    public boolean wordBreak2(String s, List<String> wordDict) {
        return inner(s,wordDict,0);
    }

    private boolean inner(String s, List<String> wordDict, int index) {
        if(index >= s.length()) return true;
        if (dict.get(index) != null) return dict.get(index);
        String subString = s.substring(index);
        for(int i =0;i<wordDict.size();i++) {
            String temp = wordDict.get(i);
            if (subString.startsWith(temp) && inner(s,wordDict,index+ temp.length())) {
                return true;
            } else {
                dict.put(index, false);
            }

        }
        return false;
    }

    static Set<String> strings;

    //普通回溯+递归：因为会遍历每种情况，所以时间复杂度为O(n的n次方)，需要加备忘录memo
    private static boolean wordBreak(String s, List<String> wordDict) {

        boolean flag ;

        //将List<String>变成HashSet，增加查询效率
        strings = new HashSet<>(wordDict);
        //备忘录，memo秩i代表以i为开始的子串无论是否被拆分，可以在字典中找到单词
        Boolean[] memo = new Boolean[s.length()];
        flag = recursion(s,0,memo);
        return flag;
    }

    private static boolean recursion(String s,int startIndex,Boolean[] memo){
        //递归基
        if (startIndex >= s.length()) return true;

        //查备忘录，减少重复计算，当前递归的以startIndex开始的子串，是否可以在字典中找到单词
        if (memo[startIndex] != null) return memo[startIndex];

        //递归体
        for (int i = startIndex;i<s.length();i++){
            //如果该子串在字典中
            if (strings.contains(s.substring(startIndex,i+1))){
                //继续递归剩下的部分
                boolean flag = recursion(s,i+1,memo);
                //如果剩下的部分也是在字典中，则返回true，并且在备忘录中增加记录，代表以该startIndex为开始的子串可以在字典中找到
                if (flag) {
                    memo[startIndex] = true;
                    return true;
                }
                else continue;//如果该子串不在字典中，,继续遍历
            }
        }
        //如果遍历完剩下的部分都没找到字典中的单词，则直接返回false，没有从startIndex开始的子串对应的单词，并给备忘录做好记录
        memo[startIndex] = false;
        return false;
    }


    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        String s2 = "catsandog";
        List<String> wordDict2 = new ArrayList<>();
        wordDict2.add("cats");
        wordDict2.add("dog");
        wordDict2.add("sand");
        wordDict2.add("and");
        wordDict2.add("cat");
        System.out.println(new Test139().wordBreak2(s,wordDict2));
    }


}
