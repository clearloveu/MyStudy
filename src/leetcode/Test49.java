package leetcode;

import java.util.*;

/**
 * @author zg
 * @create 2019-12-22 14:40
 *
 * 字母异位词分组[中等]
 *
 *  给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * 说明：
 * 	所有输入均为小写字母。
 * 	不考虑答案输出的顺序。
 *
 */
public class Test49 {
    private static List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for (int i =0;i<strs.length;i++){
            String key = sort(strs[i]);
            if (!map.containsKey(key)){
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(key,list);
            }else {
                List<String> temp = map.get(key);
                temp.add(strs[i]);
                map.put(key,temp);
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (String key : map.keySet()){
            res.add(map.get(key));
        }
        return res;
    }
    // 对字母排序
    private static String sort(String string){
        char[] characters = string.toCharArray();
        Arrays.sort(characters);
        return new String(characters);
    }


    //思路1：排序数组分类：当且仅当它们的排序字符串相等时，两个字符串是字母异位词。维护一个映射 ans : {String -> List}，
    // 其中每个键K是一个排序字符串，每个值是初始输入的字符串列表，排序后等于K。
    //思路2：按计数分类：当且仅当它们的字符计数（每个字符的出现次数）相同时，两个字符串是字母异位词。
    //思路3：字符串即数，用质数表示26个字母，把字符串的各个字母相乘，这样可保证字母异位词的乘积必定是相等的。（数据结构第11章的最后的思想）
    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> answers = groupAnagrams(strs);
        System.out.println(answers.toString());
    }
}
