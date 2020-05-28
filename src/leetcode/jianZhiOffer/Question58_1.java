package leetcode.jianZhiOffer;

/**
 * @author zg
 * @create 2020-03-12 23:15
 *
 * 翻转单词顺序[简单]
 *
 */
public class Question58_1 {
    public String reverseWords(String s) {
        s = s.trim();
        //"a good   example"----------->  {"a","good","","","example"}
        String[] strings =s.split(" ");  //中间可能有多个空格，要去掉只剩下一个，这里没有处理
        StringBuilder sb = new StringBuilder();
        for (int i = strings.length-1; i >=0 ; i--) {
            if(!strings[i].equals("")){
                sb.append(strings[i]);
                sb.append(" ");
            }

        }
        return sb.toString().trim();
        //另一种解法见剑指offer：两次翻转字符串
    }

    public static void main(String[] args) {
        new Question58_1().reverseWords("a good   example");
    }
}
