package leetcode;

/**
 * @author zg
 * @create 2019-12-05 21:38
 *
 * 找出字符串中第一个匹配项的下标[简单]
 *
给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。



示例 1：

输入：haystack = "sadbutsad", needle = "sad"
输出：0
解释："sad" 在下标 0 和 6 处匹配。
第一个匹配项的下标是 0 ，所以返回 0 。
示例 2：

输入：haystack = "leetcode", needle = "leeto"
输出：-1
解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。

 *
 *
 */
public class Test28 {
    public static int strStr(String haystack, String needle) {
        // 正则可能有特殊字符
        for (int i = 0; i < haystack.length() - needle.length() +1; i++) {
            boolean equals = haystack.substring(i, needle.length() + i).equals(needle);
            if (equals) {
                return i;
            }
        }
        return -1;
        // kmp算法
    }


    public static void main(String[] args) {
        System.out.println(strStr("sadbutsad" , "sad"));
    }

}
